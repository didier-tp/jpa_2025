package tp.myJpa.dao;

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
	
	public Employe rechercherEmployeParId(Long id) {
		//....
		return null;
	}
	
	public void supprimerEmployeParId(Long id) {
		//....
	}
	
    public Employe modifierEmloye(Employe e) {
    	//...
		return e;
	}
	

}
