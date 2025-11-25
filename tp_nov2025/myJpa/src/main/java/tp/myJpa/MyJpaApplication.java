package tp.myJpa;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import tp.myJpa.dao.DaoEmploye;
import tp.myJpa.entity.Employe;

@SpringBootApplication
public class MyJpaApplication {

	public static void main(String[] args) {
		//initialisation de Spring (via fichier application.properties)
		//attention : en springBoot moderne le fichier META-INF/persistence.xml n'est plus analysé
		ApplicationContext contexteSpring = SpringApplication.run(MyJpaApplication.class, args);
		
		//accès au composant DaoEmploye géré par spring (surtout pas de new ...):
		DaoEmploye daoEmploye = contexteSpring.getBean(DaoEmploye.class);
		
		//utilisation du dao (qui utilise EntityManager en interne):
		Employe emp1 = new Employe(null,"didier","Defrance","d2f.defrance@gmail.com");
		daoEmploye.insertNew(emp1);
		
		List<Employe> listeEmployes = daoEmploye.findAll();
		
		
		//System.out.println("listeEmployes = " + listeEmployes );
		for(Employe e : listeEmployes) {
			System.out.println(e.toString());
		}
	}

}
