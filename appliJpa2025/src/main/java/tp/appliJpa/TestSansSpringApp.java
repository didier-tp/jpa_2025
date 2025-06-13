package tp.appliJpa;

import java.util.List;
import java.util.Properties;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import tp.appliJpa.entity.Employe;
import tp.appliJpa.repository.RepositoryEmployeJpaSansSpring;

// classe de démarrage de l'application (sans utiliser spring)
public class TestSansSpringApp {
	public static void main(String[] args) {
		Properties props = new Properties();
		props.setProperty("jakarta.persistence.jdbc.user", "root");
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory("appliSpringJpa");
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("appliSpringJpa",props);
       //NB: appliSpringJpa= name du persistent-unit configuré dans META-INF/persistence.xml
		
		EntityManager entityManager = emf.createEntityManager();
		RepositoryEmployeJpaSansSpring daoEmployeJpa = new RepositoryEmployeJpaSansSpring();
		daoEmployeJpa.setEntityManager(entityManager);
		Employe emp1 = new Employe(null, "prenom1", "Nom", "0102030405", "jean.Bon@xyz.com", "login", "pwd");
		daoEmployeJpa.insertNew(emp1);
		List<Employe> employes = daoEmployeJpa.findAll();
		for (Employe emp : employes) {
			System.out.println(emp);
		}
		entityManager.close();
		emf.close();
	}

}
