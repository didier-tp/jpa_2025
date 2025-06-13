package tp.appliJpa.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tp.appliJpa.entity.Employe;

@SpringBootTest   //à lancer avec Run as ... / JUnit Test
class TestRepositoryEmploye {
	
	@Autowired //equivalent de @Inject	
	private RepositoryEmploye repositoryEmploye;

	@Test
	void testInsertionEtRelecture() {
		Employe emp1 = new Employe(null, "prenom1", "Nom", "0102030405", "jean.Bon@xyz.com", "login", "pwd");
		repositoryEmploye.insertNew(emp1);
		Assertions.assertNotNull(emp1.getId());
		List<Employe> employes = repositoryEmploye.findAll();
		for (Employe emp : employes) {
			System.out.println(emp);
		}
		Assertions.assertTrue(employes.size()>=1);
		
		Employe e = repositoryEmploye.findEmployeByEmail("jean.Bon@xyz.com");
		Assertions.assertNotNull(e);
		Assertions.assertEquals("jean.Bon@xyz.com",e.getEmail());
		System.out.println("employe avec email=jean.Bon@xyz.com : " + e.toString());
	}

}
