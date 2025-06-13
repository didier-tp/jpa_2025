package tp.appliJpa.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import tp.appliJpa.entity.Client;
import tp.appliJpa.entity.Compte;
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
		// .fetch , .join !!!! API JPA criteria = API de @!
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Compte> cq = cb.createQuery(Compte.class);
		Root<Compte> root = cq.from(Compte.class);
		root.fetch("operations",JoinType.LEFT);
		cq.select(root);
		//.distinct(true);
		cq.where(cb.equal( root.get("numero"), numCompte));
		return entityManager.createQuery(cq).getSingleResult();
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

	
}