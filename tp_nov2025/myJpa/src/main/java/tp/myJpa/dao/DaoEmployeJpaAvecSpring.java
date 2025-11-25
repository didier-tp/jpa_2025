package tp.myJpa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
//@Transacational en verson spring (ou jakarta)
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import tp.myJpa.dao.generic.DaoGenericJpa;
import tp.myJpa.entity.Employe;

/*
 * DAO = Data Access Object = objet de traitement spécialisé dans l'accès aux données
 * avec méthodes "CRUD" (Create , Read , Update , Delete)
 */

@Repository //@Component Spring de type DAO 
@Transactional //pour entityManager.getTransaction().begin() , .commit() , .rollback() automatiques
public class DaoEmployeJpaAvecSpring extends DaoGenericJpa<Employe,Long> implements DaoEmploye {
	
	public DaoEmployeJpaAvecSpring() {
		super(Employe.class);
	}

	@PersistenceContext //pour automatiquement créer et utiliser le entityManager selon config xml ou spring
	private EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	//exercice (TP) : reprogrammer en francais certaines méthodes héritées pour s'entrainer:
	
	public Employe rechercherEmployeParId(Long id) {
		return entityManager.find(Employe.class, id);  //declenche à peu près  SELECT .... WHERE e.id=id
	}
	
	public void supprimerEmployeParId(Long id) {
		Employe eASupprimer = entityManager.find(Employe.class, id);
		entityManager.remove(eASupprimer); //declenche DELETE SQL
	}
	
    public Employe modifierEmloye(Employe e) {
    	entityManager.merge(e); //declenche UPDATE SQL
		return e;
	}
    
    
    public List<Employe> findByFirstName(String firstName){
    	//à coder et à appeler (coté test) en TP:
    	return entityManager.createQuery("SELECT e FROM Employe e WHERE e.firstName = :prenom" , Employe.class)
    			.setParameter("prenom", firstName)
    			.getResultList();
    }
    
    public Employe findByEmail(String email){
    	//à coder et à appeler (coté test) en TP:
    	return entityManager.createQuery("SELECT e FROM Employe e WHERE e.email = :email" , Employe.class)
    			.setParameter("email", email)
    			.getSingleResult();
    }
	

}
