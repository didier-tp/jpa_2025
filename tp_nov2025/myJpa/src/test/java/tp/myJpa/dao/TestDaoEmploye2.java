package tp.myJpa.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tp.myJpa.entity.Employe;

@SpringBootTest
public class TestDaoEmploye2 {
	
	@Autowired
	private DaoEmployeJpaAvecSpring daoEmploye;
	
	@Test public void testAvecSpring() {
		//séquence de test idéale:
		
		//1. créer une nouvelle chose
		Employe emp1 = new Employe(null,"prenom1","Nom","jean.Bon@xyz.com");
		daoEmploye.insertNew(emp1);
		Long idEmp = emp1.getId(); //clef primaire auto incrémentée du nouvel employé ajouté en base
		
		//2. afficher tout pour vérifier l'ajout
		List<Employe> employes = daoEmploye.findAll();
		for(Employe emp : employes) {System.out.println(emp);}

		//3. récupérer une entité précise via sa clef primaire et l'afficher
		Employe emp1Relu = daoEmploye.findById(idEmp);
		System.out.println("emp1Relu=" + emp1Relu);
		System.out.println("avec email=" + emp1Relu.getEmail());

		//4. modifier les valeurs en mémoire puis en base
		emp1Relu.setEmail("jean.bon@abc.com");
		daoEmploye.update(emp1Relu);

		//5. re-déclencher étape 3 pour vérifier la mise à jour en base
		Employe emp1Relu2 = daoEmploye.findById(idEmp);
		System.out.println("emp1Relu2 apres update =" + emp1Relu2);

		//6. supprimer la chose ajoutée à l'étape 1
		daoEmploye.deleteById(idEmp);

		//7. on vérifie que ça n'existe plus
		Employe emp1Relu3NormalementNull = daoEmploye.findById(idEmp);
		if(emp1Relu3NormalementNull==null)
			System.out.println("employe bien supprimé dans la base de données"); 
	}
}
