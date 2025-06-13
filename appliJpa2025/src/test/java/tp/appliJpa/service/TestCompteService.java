package tp.appliJpa.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tp.appliJpa.entity.Compte;
import tp.appliJpa.repository.RepositoryCompte;

@SpringBootTest   //à lancer avec Run as ... / JUnit Test
class TestCompteService {
	
	@Autowired //equivalent de @Inject	
	private RepositoryCompte repositoryCompte;
	
	
	@Autowired //equivalent de @Inject	
	private CompteService compteService;
	
	/*
	@Autowired //equivalent de @Inject	
	private RepositoryOperation repositoryOperation;
	
	@Autowired //equivalent de @Inject	
	private RepositoryClient repositoryClient;
	*/
	@Test
	void testBonVirement() {
		Compte compteC1 = repositoryCompte.insertNew(new Compte(null,"compteC1" , 101.0));
		Compte compteC2 = repositoryCompte.insertNew(new Compte(null,"compteC2" , 202.0));
		System.out.println("avant bon virement: solde c1="+compteC1.getSolde());
		System.out.println("avant bon virement: solde c2="+compteC2.getSolde());
		compteService.effectuerVirement(50.0, compteC1.getNumero(), compteC2.getNumero());
		Compte compteC1ReluApresVirement = repositoryCompte.findById(compteC1.getNumero());
		Compte compteC2ReluApresVirement = repositoryCompte.findById(compteC2.getNumero());
		System.out.println("apres bon virement: solde c1="+compteC1ReluApresVirement.getSolde());
		System.out.println("apres bon virement: solde c2="+compteC2ReluApresVirement.getSolde());
		Assertions.assertEquals(compteC1.getSolde() - 50, compteC1ReluApresVirement.getSolde());
		Assertions.assertEquals(compteC2.getSolde() + 50, compteC2ReluApresVirement.getSolde());
	}

	@Test
	void testMauvaisVirement() {
		Compte compteC1 = repositoryCompte.insertNew(new Compte(null,"compteC1" , 101.0));
		Compte compteC2 = repositoryCompte.insertNew(new Compte(null,"compteC2" , 202.0));
		System.out.println("avant mauvais virement: solde c1="+compteC1.getSolde());
		System.out.println("avant mauvais virement: solde c2="+compteC2.getSolde());
		try {
			compteService.effectuerVirement(50.0, compteC1.getNumero(), -78);
			//le compte à créditer -78 n'existe pas
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		Compte compteC1ReluApresVirement = repositoryCompte.findById(compteC1.getNumero());
		Compte compteC2ReluApresVirement = repositoryCompte.findById(compteC2.getNumero());
		System.out.println("apres mauvais virement: solde c1="+compteC1ReluApresVirement.getSolde());
		System.out.println("apres mauvais virement: solde c2="+compteC2ReluApresVirement.getSolde());
		Assertions.assertEquals(compteC1.getSolde(), compteC1ReluApresVirement.getSolde());
		Assertions.assertEquals(compteC2.getSolde(), compteC2ReluApresVirement.getSolde());
	}
	
}
