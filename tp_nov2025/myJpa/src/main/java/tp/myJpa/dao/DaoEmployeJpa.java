package tp.myJpa.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import tp.myJpa.entity.Employe;

/*
 * DAO = Data Access Object = objet de traitement spécialisé dans l'accès aux données
 * avec méthodes "CRUD" (Create , Read , Update , Delete)
 */

public class DaoEmployeJpa implements DaoEmploye {
	
	private EntityManager entityManager;
	
    //pourra etre appelé par le main() sans Spring:
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Employe> findAll() {
		return entityManager.createQuery("SELECT e FROM Employe e",Employe.class).getResultList();
	}

	@Override
	public Employe insertNew(Employe e) {
		//au debut e.getId() vaut null (id inconnu)
		entityManager.getTransaction().begin();
		entityManager.persist(e); //.persist() va modifier l'objet e si @GeneratedValue(.... ) et auto_increment
		entityManager.getTransaction().commit();
		return e; //en retour e avec sous partie .getId() pas null
	}

}
