package tp.myJpa.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tp.myJpa.entity.Employe;

@SpringBootTest
public class TestDaoEmploye {
	
	@Autowired
	private DaoEmploye daoEmploye;
	
	@Test
	public void testAjoutEtRelecture(){
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
