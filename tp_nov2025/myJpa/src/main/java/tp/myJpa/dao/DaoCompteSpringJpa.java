package tp.myJpa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import tp.myJpa.dao.generic.DaoGenericJpa;
import tp.myJpa.entity.Client;
import tp.myJpa.entity.Compte;

@Repository
@Transactional
public class DaoCompteSpringJpa extends DaoGenericJpa<Compte,Long> implements DaoCompte {
	
	@PersistenceContext
	private EntityManager entityManager;

	public DaoCompteSpringJpa() {
		super(Compte.class);
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	//+code d'éventuels .find... spécifiques

	/*
	@Override
	public Compte findWithOperationsById(long numCompte) {
		//PREMIERE VERSION (PAS BIEN!!!!!)
		Compte compte = entityManager.find(Compte.class, numCompte);
		for(Operation op : compte.getOperations()) {
			//boucle for (à vide) pour remonter en mode lazy
			//les valeurs de la collection operation en mémoire
			//avant que ce ne soit trop tard (avant que EJB ou Spring ferme
			//automatiquement le entityManager).
		}
		return compte;
	}
	*/



    @Override
    public Compte findWithOperationsById(long numCompte) {
        //SECONDE VERSION (BIEN/MIEUX) :  Query avec mot clef fetch


		return entityManager.createQuery("SELECT c FROM Compte c LEFT JOIN FETCH c.operations WHERE c.numero = :numCompte", Compte.class)
	            .setParameter("numCompte", numCompte)
	            .getSingleResult();

    }

	@Override
	public List<Compte> findByClientId(long idClient) {
		return entityManager.createQuery("SELECT cpt FROM Client cli LEFT JOIN  cli.comptes cpt WHERE cli.id = :idClient",Compte.class)
				.setParameter("idClient", idClient)
				.getResultList();
	}
	
	public List<Compte> findComptesByNumCli(long numCli) {
	
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Compte> criteriaQuery = cb.createQuery(Compte.class);
		Root<Client> clientRoot = criteriaQuery.from(Client.class);
		Predicate pEqNumCli = cb.equal(clientRoot.get("id") , numCli);
		//Predicate pEqNumCli = cb.equal(clientRoot.get(Client_.id) , numCli);
		Join<Client, Compte> joinComptesOfClient = clientRoot.join("comptes");
		//Join<Client, Compte> joinComptesOfClient = clientRoot.join(Client_.comptes);
		criteriaQuery.select(joinComptesOfClient);
		criteriaQuery.where(pEqNumCli);
		return this.entityManager.createQuery(criteriaQuery).getResultList();
		}


}









