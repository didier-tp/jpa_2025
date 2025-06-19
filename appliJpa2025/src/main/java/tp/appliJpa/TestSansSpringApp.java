package tp.appliJpa;

import java.util.List;
import java.util.Properties;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
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
		//NB: à ce stade , l'objet emp1 qui vient d'être créé à coup de new n'a pas encore été passé à la couche de persistance gérée par JPA
		//on dit que cet objet est transient (quasiment équivalent à l'état détaché)
		if(! entityManager.contains(emp1))
			System.out.println("emp1 n'est dans l'instant pas persistant");
		
		daoEmployeJpa.insertNew(emp1);
		//maintenant que l'objet emp1 a été passé à daoEmployeJpa.insertNew() qui a en interne déclenché un appel à entityManager.persist()
		//emp1 est à l'état persistant
		
		//NB: telle qu'elle est codée, la méthode .insertNew() a ouvert et terminé une transaction (via un commit).
		//ce n'est pas la fin de la transaction qui met fin à l'état persistant 
		//mais la fermeture de l'objet entityManager ou bien un appel à entityManager.clear() ou bien entityManager.detach() 
		
		//Attention: dans un contexte ordinaire ou JPA est intégré dans un framework compatible JEE (ex: EJB>=3 ou bien Spring),
		//le entityManager (souvent automatiquement initialisé via 	@PersistenceContext ou un équivalent ) EST AUTOMATIQUEMENT fermé dès 
		//que la plus grande méthode du service métier (quelquefois/idéalement préfixée par @Transactional) a terminé son travail
		//Autrement dit , un test de DAO/repository "spring" ou de service métier (appelant indirectement des sous traitements sur un DAO/Repository)
		//va souvent récupéré des données à l'état détaché car la méthode appelée par le test aura souvent tout fermé (transaction et entityManager nécessaire à la création de la transaction)
		
		//plus précisémment , si une classe de Test ordinaire appelle directement une méthode sur un DAO/repository Spring , le résultat sera quasiment toujours à l'état détaché
		//si une méthode de DAO/repository spring est appelé par un service , alors la fermeture du entityManager est différée à la fin de la transaction globale sur tout le service
		//et au sein d'un service , l'état est souvent persistent (et pas de lazy exception)
		
		if(entityManager.contains(emp1))
			System.out.println("emp1 est actuellement persistant");

		
		List<Employe> employes = daoEmployeJpa.findAll();
	
		//Tant que l'entityManager n'est pas (explicitement ou implicitement) fermé , 
		//les objets remontés par l'appel à daoEmployeJpa.findAll() sont ici à l'état PERSITANT
		//conséquences :
		//    --> pas de lazyException
		//    --> si au sein d'une (nouvelle ou pas) transaction , on s'amuserait à modifier les valeurs d'un de ces objets
		//        alors un appel (explicite ou implicite) à transaction.commit() déclencherait un appel indirect à entityManger.flush()
		//        et on aurait alors un "UPDATE SQL Automatiquement déclenché" 
		//        comme le démontre les 4 lignes suivantes:
		
		entityManager.getTransaction().begin();
		Employe dernierEmp = employes.get(employes.size()-1);
		dernierEmp.setFirstName(dernierEmp.getFirstName().toUpperCase()); //"prenom1" --> "PRENOM1" en mémoire 
		entityManager.getTransaction().commit(); //commit() --> flush() --> automatic merge/update of all modified persitent entities
		
		for (Employe emp : employes) {
			String etat = (entityManager.contains(emp))?"persistant":"detaché";
			System.out.println(emp + " a l'état " + etat);
		}
		
		entityManager.close();
		
		// emp1 et tous les autres objets ne sont maintenant plus persistants car l'entityManager a été fermé
		// ces instances de la classe Employe existent encore en mémoire (avec les bonnes valeurs)
		//on dit que ces objets sont à l'état détaché
		//on ne peut malheureusement pas appeler entityManager.contains(emp1) pour vérifier cela 
		//car entityManager.contains() ne focntionne pas quand l'entityManager est fermé
		
		
		
		emf.close();
	}

}
