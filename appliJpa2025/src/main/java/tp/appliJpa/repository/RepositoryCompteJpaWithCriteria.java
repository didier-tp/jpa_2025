package tp.appliJpa.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import tp.appliJpa.entity.Client;
import tp.appliJpa.entity.Compte;
import tp.appliJpa.entity.Operation;
import tp.appliJpa.repository.generic.RepositoryGenericJpa;

@Repository   
@Transactional 
@Qualifier("withCriteria")
public class RepositoryCompteJpaWithCriteria extends RepositoryGenericJpa<Compte,Long>
                   implements RepositoryCompte {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	public RepositoryCompteJpaWithCriteria() {
		super(Compte.class);
	}
	
	
	
	@Override
	public Compte update(Compte e) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaUpdate<Compte> compteCriteriaUpdate = cb.createCriteriaUpdate(Compte.class);
		Root<Compte> root = compteCriteriaUpdate.from(Compte.class);
		compteCriteriaUpdate.set("label", e.getLabel());
		compteCriteriaUpdate.set("solde", e.getSolde());
		compteCriteriaUpdate.where(cb.equal(root.get("numero"), e.getNumero()));
		entityManager.createQuery(compteCriteriaUpdate).executeUpdate();
		return e;
	}

	@Override
	public void deleteById(Long id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaDelete<Compte> compteCriteriaDelete = cb.createCriteriaDelete(Compte.class);
		Root<Compte> root = compteCriteriaDelete.from(Compte.class);
		compteCriteriaDelete.where(cb.equal(root.get("numero"), id));
		entityManager.createQuery(compteCriteriaDelete).executeUpdate();
	}

	@Override
	public List<Compte> findAll() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Compte> cq = cb.createQuery(Compte.class);
		Root<Compte> root = cq.from(Compte.class);
		cq.select(root);
		return entityManager.createQuery(cq).getResultList();
	}
	
	@Override
	public List<Compte> findBySoldeMini(double soldeMini) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Compte> cq = cb.createQuery(Compte.class);
		Root<Compte> root = cq.from(Compte.class);
		cq.select(root);
		cq.where(cb.greaterThanOrEqualTo( root.get("solde"), soldeMini));
		return entityManager.createQuery(cq).getResultList();
	}

	
	@Override
	public Compte findWithOperationsById(long numCompte) {
		// Important , en mode "api criteria" , pas de mot clef "FETCH" dans une requête JPQL mais api complémentaire "EntityGraph"
		EntityGraph entityGraph = entityManager.getEntityGraph("entity-graph-compte-operations");
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Compte> cq = cb.createQuery(Compte.class);
		Root<Compte> root = cq.from(Compte.class);
		cq.select(root);
		cq.where(cb.equal( root.get("numero"), numCompte));
		return entityManager.createQuery(cq)
							.setHint("jakarta.persistence.loadgraph", entityGraph)//or "javax.persistence.loadgraph"
				            .getSingleResult();
		//NB: spring.jpa.show-sql=true (temporairement)
		//---> c'est simple, ça marche bien mais pas de filtrage sur les opérations , version plus élaborée: findWithSmallOperationsById(long numCompte, double maxAmount)
	}

	@Override
	public List<Compte> findByClientId(long idClient) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Compte> cq = cb.createQuery(Compte.class);
		Root<Client> clientRoot = cq.from(Client.class);
		cq.select(clientRoot.join("comptes"));
		cq.where(cb.equal(clientRoot.get("id"), idClient));
		return entityManager.createQuery(cq).getResultList();
	}

	@Override
	public Compte findWithSmallOperationsById(long numCompte, double maxAmount) {
		//NB: ce code semble fonctionner , mais il est compliqué et il y a peut être plus simple ...
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		Root<Compte> root = cq.from(Compte.class);
		Join<Compte,Operation> joinedOperationsOfCompte = root.join("operations" , JoinType.LEFT);
		
		//NB cq.multiselect() déclenche une seule requete SQL mais récupére les différentes parties demandées
		//en tant que multiples sous parties d'un ou plusieurs Tuple (selon appel ultérieur à .getSingleResult ou .getResultList())
		//les différentes parties du tuple sont soit indexées (0,1,2,...) ou bien nommées via des alias.
		cq.multiselect(root.alias("compte"),
				       joinedOperationsOfCompte.alias("attached_operation"));
		
		cq.where(cb.equal( root.get("numero"), numCompte),
				 cb.lessThanOrEqualTo( joinedOperationsOfCompte.get("montant"), maxAmount),
				 cb.greaterThanOrEqualTo( joinedOperationsOfCompte.get("montant"), -maxAmount)
				 );
		//cq.distinct(true);
		
		//Récupération du résultat de la requête multiselect avec jointure:
		List<Tuple> list_tupleCompteOperation = entityManager.createQuery(cq).getResultList();
		Tuple firstTuple = list_tupleCompteOperation.get(0);
		//extraction de la sous partie compte du résultat
		//Compte c = firstTuple.get(0, Compte.class); //by index
		Compte c = firstTuple.get("compte", Compte.class); //by alias
		List<Operation> operations = new ArrayList<Operation>();
		//extraction de la sous partie operations rattachées du résultat:
		for(Tuple tupleCompteOperation :list_tupleCompteOperation) {
			//operations.add(tupleCompteOperation.get(1,Operation.class));//by index
			operations.add(tupleCompteOperation.get("attached_operation",Operation.class));//by alias
		}
        //rattachement des parties récupérées
		c.setOperations(operations);
		return c;
		
	}

	
}