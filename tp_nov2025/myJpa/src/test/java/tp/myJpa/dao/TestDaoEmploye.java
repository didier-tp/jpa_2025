package tp.myJpa.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tp.myJpa.entity.Employe;

@SpringBootTest
public class TestDaoEmploye {
	
	@Autowired
	private DaoEmployeJpaAvecSpring daoEmploye;
	
	@Test
	public void testRecherches(){
		 Employe e = daoEmploye.findByEmail("alain.therieur@xyz.com");
		 System.out.println("res de findByEmail:" + e);
		 
		 List<Employe> listeEmp = daoEmploye.findByFirstName("didier");
		 System.out.println("res de findByFirstName:" + listeEmp);
	}
	
	@Test
	public void testAjoutEtRelecture(){
		        //utilisation du dao (qui utilise EntityManager en interne):
				Employe emp1 = new Employe(null,"didier","Defrance","d2f.defrance@gmail.com");
				daoEmploye.insertNew(emp1);
				Long idEmp1 = emp1.getId(); //id plus null (auto incrémenté) de l'employé inséré:
				System.out.println("idEmp1="+idEmp1);
				
				//relecture pour vérifier ajout:
				Employe emp1Relu = daoEmploye.rechercherEmployeParId(idEmp1);
				System.out.println("emp1Relu (après ajout)" + emp1Relu);
				
				//modification:
				emp1Relu.setLastName("toto");
				daoEmploye.modifierEmloye(emp1Relu);
				
				//relecture pour vérifier update:
				emp1Relu = daoEmploye.rechercherEmployeParId(idEmp1);
				System.out.println("emp1Relu (après update) " + emp1Relu);
				
				//suppression:
				daoEmploye.deleteById(idEmp1);
				
				//relecture à null pour vérifier suppression:
				emp1Relu = daoEmploye.rechercherEmployeParId(idEmp1);
				System.out.println("emp1Relu (après suppression) normalement null: " + emp1Relu);
				
				
				List<Employe> listeEmployes = daoEmploye.findAll();
				
				
				//System.out.println("listeEmployes = " + listeEmployes );
				for(Employe e : listeEmployes) {
					System.out.println(e.toString());
				}
	}
	
	public void testAvecSpring() {
		
	}
}
