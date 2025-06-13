package tp.appliJpa.repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import tp.appliJpa.entity.Employe;

/*
* Version "Sans spring" :
* - pas de @PersistenceContext
* - setEntityManager
* - gestion explicite des transactions entityManager.getTransaction()....
*/
public class RepositoryEmployeJpaSansSpring implements RepositoryEmploye {
	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Employe findById(Long code) {
		return entityManager.find(Employe.class, code);
//SELECT .... WHERE empId=...
	}

	@Override
	public List<Employe> findAll() {
		return entityManager.createQuery("SELECT e FROM Employe e", Employe.class).getResultList();
	}

	@Override
	public Employe insertNew(Employe emp) {
		try {
			entityManager.getTransaction().begin();
//en entrée , emp est un nouvel objet employé avec .empId à null (encore inconnu)
//déclenche automatiquement INSERT INTO Employe(firstname, ....) VALUES(emp.getFirstname() , ....)
			entityManager.persist(emp);// .empId n'est normalement plus null si auto-incr
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw new RuntimeException("echec insertNew(employe)");
		}
		return emp; // on retourne l'objet modifié (avec .empId non null)
	}

	@Override
	public Employe update(Employe emp) {
		entityManager.getTransaction().begin();
		entityManager.merge(emp);
		// déclenche automatiquement UPDATE Employe set .... WHERE idEmp=code
		entityManager.getTransaction().commit();// à peaufiner via try/catch
		return emp;
	}

	@Override
	public void deleteById(Long code) {
		entityManager.getTransaction().begin();
		Employe empAsupprimer = entityManager.find(Employe.class, code);
		entityManager.remove(empAsupprimer);
		// déclenche automatiquement DELETE FROM Employe WHERE idEmp=code
		entityManager.getTransaction().commit(); // à peaufiner via try/catch
	}

	@Override
	public Employe findEmployeByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}