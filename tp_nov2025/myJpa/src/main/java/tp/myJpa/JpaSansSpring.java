package tp.myJpa;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import tp.myJpa.dao.DaoEmployeJpa;
import tp.myJpa.entity.Employe;

public class JpaSansSpring {

	public static void main(String[] args) {
		System.out.println("appli jpa");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myJpa");
		//NB: appliSpringJpa= name du persistent-unit configuré dans META-INF/persistence.xml
		EntityManager entityManager = emf.createEntityManager();
		
		/*
		entityManager.getTransaction().begin();
		Employe emp1 = new Employe(null,"didier","Defrance","d2f.defrance@gmail.com");
		entityManager.persist(emp1);
		entityManager.getTransaction().commit();
		
		List<Employe> listeEmployes = entityManager.createQuery("SELECT e FROM Employe e",Employe.class).getResultList();
		*/
		
		DaoEmployeJpa daoEmploye = new DaoEmployeJpa();
		daoEmploye.setEntityManager(entityManager);
		Employe emp1 = new Employe(null,"didier","Defrance","d2f.defrance@gmail.com");
		daoEmploye.insertNew(emp1);
		
		List<Employe> listeEmployes = daoEmploye.findAll();
		
		
		//System.out.println("listeEmployes = " + listeEmployes );
		for(Employe e : listeEmployes) {
			System.out.println(e.toString());
		}
		
		entityManager.close();
		emf.close();
    
	}

}
