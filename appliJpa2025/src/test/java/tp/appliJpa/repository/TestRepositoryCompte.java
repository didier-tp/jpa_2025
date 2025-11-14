package tp.appliJpa.repository;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tp.appliJpa.entity.Client;
import tp.appliJpa.entity.Compte;
import tp.appliJpa.entity.CompteEpargne;
import tp.appliJpa.entity.Operation;

@SpringBootTest   //à lancer avec Run as ... / JUnit Test
class TestRepositoryCompte {
	
	@Autowired //equivalent de @Inject	
	private RepositoryCompte repositoryCompte;
	
	@Autowired //equivalent de @Inject	
	private RepositoryOperation repositoryOperation;
	
	@Autowired //equivalent de @Inject	
	private RepositoryClient repositoryClient;
	
	@Test
	void testComptesDeClient() {
		
		Compte compteC1 = repositoryCompte.insertNew(new Compte(null,"compteC1" , 101.0));
		//Compte compteC2 = repositoryCompte.insertNew(new Compte(null,"compteC2" , 202.0));
		Compte compteC2 = repositoryCompte.insertNew(new CompteEpargne(null,"compteEpargneC2" , 202.0,1.5));
		Compte compteC3 = repositoryCompte.insertNew(new Compte(null,"compteC3" , 303.0));
		
		addOperationInDatabase(compteC1,new Operation(null,"achat c1o1",-4.6));
		addOperationInDatabase(compteC1,new Operation(null,"achat c1o2",-1.7));
		addOperationInDatabase(compteC2,new Operation(null,"achat c2o1",-2.6));
	
		
		Client cliX = new Client(null,"prenomX" , "nomX");
		cliX.getComptes().add(compteC1);
		cliX.getComptes().add(compteC2);
		repositoryClient.insertNew(cliX);
		
		
		//List<Compte> comptes = repositoryCompte.findAllByType("CompteCourant");
		
		List<Compte> comptes = repositoryCompte.findByClientId(cliX.getId());
		System.out.println("comptes du client X:");
		for (Compte cpt : comptes) {
			System.out.println("\t" +cpt);
		}
		Assertions.assertTrue(comptes.size()==2);
		
		/*
		//via RepositoryClient.clientAvecComptes et entityGraph:
		Client clientAvecComptes = repositoryClient.clientAvecComptes(cliX.getId());
		System.out.println("via RepositoryClient.clientAvecComptes() ,clientAvecComptes= "+clientAvecComptes);
		*/
	
		//via RepositoryClient.clientAvecComptes et entityGraph:
		Client clientAvecComptesEtOperations = repositoryClient.clientAvecComptesEtOperations(cliX.getId());
		System.out.println("via RepositoryClient.clientAvecComptesEtOperations() ,clientAvecComptesEtOperations= "+clientAvecComptesEtOperations);
		Client clientAvecComptes = clientAvecComptesEtOperations;
		
		System.out.println("comptes de ce client:");
		for (Compte cpt : clientAvecComptes.getComptes()) {
			System.out.println("\t" +cpt);
			for (Operation op : cpt.getOperations()) {
				System.out.println("\t\t" +op);
			}
		}
		Assertions.assertTrue(clientAvecComptes.getComptes().size()==2);
		
	}
	
	private void addOperationInDatabase(Compte cpt,Operation op) {
		op.setCompte(cpt);
		repositoryOperation.insertNew(op);
	}

	@Test
	void testOperationsDeCompte() {
		Compte compteQueJaime = new Compte(null,"compteFavori" , 999999.99);
		repositoryCompte.insertNew(compteQueJaime);
		Long numCompte = compteQueJaime.getNumero();
		
		//Operation opA = new Operation(null,"achat_a",-5.0,new Date(),compteQueJaime);
		Operation opA = new Operation(null,"achat_a",-5.0); 
		opA.setCompte(compteQueJaime);
		repositoryOperation.insertNew(opA);
		//Operation opB= new Operation(null,"achat_b",-6.0,new Date(),compteQueJaime);
		Operation opB= new Operation(null,"achat_b",-6.0);
		opB.setCompte(compteQueJaime);
		repositoryOperation.insertNew(opB);
		
		//Compte compteRelu = repositoryCompte.findById(numCompte);//avec lazy exception
		Compte compteRelu = repositoryCompte.findWithOperationsById(numCompte);
		List<Operation> operations = compteRelu.getOperations();
		for (Operation op : operations) {
			System.out.println(op);
		}
		Assertions.assertTrue(operations.size()>=1);
	}
	
	//@Test
	void testOperationsDeCompteQuiFonctionnePas() {
		Operation opA = new Operation(null,"achat_a",-5.0,new Date(),null);
		repositoryOperation.insertNew(opA);
		Operation opB= new Operation(null,"achat_b",-6.0,new Date(),null);
		repositoryOperation.insertNew(opB);
		
		Compte compteQueJaime = new Compte(null,"compteFavori" , 999999.99);
		compteQueJaime.getOperations().add(opA);
		compteQueJaime.getOperations().add(opB);
		repositoryCompte.insertNew(compteQueJaime); 
		//on sauvegarde en base ici le coté secondaire (là où il y a mappedBy)
		//ET SANS CASCADE , CA NE FONCTIONNE PAS EN JPA/Hibernate !!!!
		//La clef étrangère est mal sauvegardée en base (reste à NULL)
		
		Long numCompte = compteQueJaime.getNumero();
		
		Compte compteRelu = repositoryCompte.findById(numCompte);
		List<Operation> operations = compteRelu.getOperations();
		for (Operation op : operations) {
			System.out.println(op);
		}
		Assertions.assertTrue(operations.size()>=1);
	}

}
