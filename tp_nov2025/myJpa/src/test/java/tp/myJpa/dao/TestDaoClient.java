package tp.myJpa.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tp.myJpa.entity.Client;
import tp.myJpa.entity.Compte;

@SpringBootTest
public class TestDaoClient {
	
	@Autowired
	private DaoCompte daoCompte;
	
	@Autowired
	private DaoClient daoClient;
	

	@Test
	void testClientAvecComptes() {
		Client cli1 = new Client(null,"Condor" , "Olie"); //mappedBy coté client
		daoClient.insertNew(cli1);
		Compte cc1 = new Compte(null,"comptecA",50.0);
		cc1.getClients().add(cli1); //JoinTable coté compte
		daoCompte.insertNew(cc1);
		Compte cc2 = new Compte(null,"comptecB",70.0 );
		cc2.getClients().add(cli1); //JoinTable coté compte
		daoCompte.insertNew(cc2); 
		
		//afficher valeurs relues pour vérifier
		Client cli1Relu=daoClient.findByIdWithComptes(cli1.getId());
		System.out.println("cli1Relu"+cli1Relu);
		for(Compte c : cli1Relu.getComptes()){
		System.out.println("\t" + c.toString());
		}
		
		//Solution2:
		System.out.println("via repositoryCompte.findByClientId(idClient):");
		for(Compte c : daoCompte.findByClientId(cli1.getId())){
		System.out.println("\t" + c.toString());
		}
		
	}

}
