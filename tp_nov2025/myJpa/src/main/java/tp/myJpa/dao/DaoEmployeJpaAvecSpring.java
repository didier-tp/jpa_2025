package tp.myJpa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

//@Transacational en verson spring (ou jakarta)
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import tp.myJpa.entity.Employe;

/*
 * DAO = Data Access Object = objet de traitement spécialisé dans l'accès aux données
 * avec méthodes "CRUD" (Create , Read , Update , Delete)
 */

@Repository //@Component Spring de type DAO 
@Transactional //pour entityManager.getTransaction().begin() , .commit() , .rollback() automatiques
public class DaoEmployeJpaAvecSpring implements DaoEmploye {
	
	@PersistenceContext //pour automatiquement créer et utiliser le entityManager selon config xml ou spring
	private EntityManager entityManager;
	

	@Override
	public List<Employe> findAll() {
		return entityManager.createQuery("SELECT e FROM Employe e",Employe.class).getResultList();
	}

	@Override
	public Employe insertNew(Employe e) {
		//au debut e.getId() vaut null (id inconnu)
		entityManager.persist(e); //.persist() va modifier l'objet e si @GeneratedValue(.... ) et auto_increment
		return e; //en retour e avec sous partie .getId() pas null
	}

}
