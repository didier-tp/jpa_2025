package tp.appliJpa.repository;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import tp.appliJpa.entity.Compte;
import tp.appliJpa.entity.Operation;
import tp.appliJpa.repository.generic.RepositoryGenericJpa;

@Repository   
@Transactional 
@Primary //version par defaut
public class RepositoryCompteJpa extends RepositoryGenericJpa<Compte,Long>
                   implements RepositoryCompte {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	public RepositoryCompteJpa() {
		super(Compte.class);
	}
	
	

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
		//SECONDE VERSION (BIEN/MIEUX) :  NamedQuery avec mot clef fetch
		
		/*
		return entityManager.createQuery("SELECT c FROM Compte c LEFT JOIN FETCH c.operations WHERE c.numero = :numCompte", Compte.class)
	            .setParameter("numCompte", numCompte)
	            .getSingleResult();
		*/
		
		return entityManager.createNamedQuery("Compte.findWithOperationsById", Compte.class)
				            .setParameter("numCompte", numCompte)
				            .getSingleResult();
	}
     
	@Override
	public List<Compte> findByClientId(long idClient) {
		return entityManager.createNamedQuery("Compte.findByClientId", Compte.class)
	            .setParameter("idClient", idClient)
	            .getResultList();
	}

	@Override
	public List<Compte> findBySoldeMini(double soldeMini) {
		return entityManager.createNamedQuery("Compte.findBySoldeMini", Compte.class)
	            .setParameter("soldeMini", soldeMini)
	            .getResultList();
	}

	@Override
	public Compte findWithSmallOperationsById(long numCompte, double maxAmount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Compte> findAllByType(String typeCompte) {
		return entityManager.createQuery("SELECT c FROM Compte c WHERE c.typeCompte = :num" , Compte.class)
				.setParameter("num", typeCompte).getResultList();
	}
}