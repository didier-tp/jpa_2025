package tp.appliJpa.repository2;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.TransactionTemplate;

import tp.appliJpa.entity2.Article;
import tp.appliJpa.entity2.Facture;
import tp.appliJpa.entity2.LigneFacture;
import tp.appliJpa.service2.ServiceFacture;


@SpringBootTest  
class TestRepositoryFactureAvecCascade {
	
	@Autowired 
	private RepositoryFacture repositoryFacture;
	
	@Autowired 
	private RepositoryArticle repositoryArticle;
	
	@Autowired 
	private RepositoryLigneFacture repositoryligneFacture;
	
	@Autowired 
	private ServiceFacture serviceFacture;
	

	@Test
	public void testFindFactureById() {
	     System.out.println("testFindFactureById");
	     Facture f = repositoryFacture.findByIdWithLines(1L);
	     System.out.println("facture 1 : " + f);
	     for(LigneFacture ligne : f.getLignes()) {
	    	 System.out.println("\t" + ligne);
	     }
	     assertNotNull(f);
	   }
	
	//@Test
	public void testAjoutFactureNouveauxArticles_via_serviceFacture() {
		
		Map<Article,Integer> mapArticleQuantite = new HashMap<>();
		mapArticleQuantite.put(new Article(null, "ArticleA", 8.2) , 2);
		mapArticleQuantite.put(new Article(null, "ArticleB", 2.8) , 5);
		
		Facture factureSauvegardee = serviceFacture.nouvelleFactureAvecMapNouveauxArticlesQuantites(mapArticleQuantite);
		assertNotNull(factureSauvegardee);
		Long numFact=factureSauvegardee.getNumero();
		System.out.println("id/numero factureSauvegardee="+numFact);
		
		//Relecture facture sauvegardee:
		Facture fRelu = repositoryFacture.findByIdWithLines(numFact);
	     System.out.println("nouvelle facture avec ou sans cascade  : " + fRelu);
	     for(LigneFacture ligne : fRelu.getLignes()) {
	    	 System.out.println("\t" + ligne);
	     }
	     assertTrue(fRelu.getLignes().size()==2);
		
	}
	
	
	@Autowired
	TransactionTemplate txTemplate;
	
	@Test
	public void testAjoutFactureNouveauxArticles_avec_cascade_sans_serviceFacture() {
		
		//txTemplate.execute(transactionStatus->{ }) to delimit spring large block of code that will be executed in a large transaction
		//entitymanager will be closed at the end of this block --> no lazy exception, no or less  "detached ..." error .
		
		Facture factureSauvegardee = 
		txTemplate.execute(transactionStatus->{
			//sequence of instruction within a global/large transaction
			//...
			Article aa= new Article(null, "ArticleA", 8.2) ;
			Article ab = new Article(null, "ArticleB", 2.8);
			
			Facture nouvelleFacture = new Facture(); 
			LigneFacture ligneFacture1 = new LigneFacture(aa,nouvelleFacture,2);
			nouvelleFacture.getLignes().add(ligneFacture1);
			LigneFacture ligneFacture2 = new LigneFacture(ab,nouvelleFacture,5);
			nouvelleFacture.getLignes().add(ligneFacture2);
			
			Facture nouvelleFacture_saved = repositoryFacture.insertNew(nouvelleFacture);
			
			//return null; //or return something //for end of txTemplate.execute() block
			return nouvelleFacture_saved;
			//transaction and entityManager will be automatically closed at the end of this code block 
			//(instead of the end of each method invoked on a spring DAO/repository or service)
		});
		
	
		assertNotNull(factureSauvegardee);
		Long numFact=factureSauvegardee.getNumero();
		System.out.println("id/numero factureSauvegardee="+numFact);
		
		//Relecture facture sauvegardee:
		Facture fRelu = repositoryFacture.findByIdWithLines(numFact);
	     System.out.println("nouvelle facture avec cascade  : " + fRelu);
	     for(LigneFacture ligne : fRelu.getLignes()) {
	    	 System.out.println("\t" + ligne);
	     }
	     assertTrue(fRelu.getLignes().size()==2);
		
	}
	
	@Test
	public void testAjoutFactureNouveauxArticles_sans_cascade_sans_serviceFacture() {
		
		//txTemplate.execute(transactionStatus->{ }) to delimit spring large block of code that will be executed in a large transaction
		//entitymanager will be closed at the end of this block --> no lazy exception, no or less  "detached ..." error .
		
		Facture factureSauvegardee = 
		txTemplate.execute(transactionStatus->{
			//sequence of instruction within a global/large transaction
			//...
			Facture nouvelleFactureWithNum = repositoryFacture.insertNew(new Facture()); //facture persistée une première fois sans ligne
			
			Article aa= new Article(null, "ArticleA", 8.2) ;
			Article savedAa  = repositoryArticle.insertNew(aa);
			Article ab = new Article(null, "ArticleB", 2.8);
			Article savedAb  = repositoryArticle.insertNew(ab);
			
			
			LigneFacture ligneFacture1 = new LigneFacture(savedAa,nouvelleFactureWithNum,2);
			repositoryligneFacture.insertNew(ligneFacture1);
			nouvelleFactureWithNum.getLignes().add(ligneFacture1);
			
			
			LigneFacture ligneFacture2 = new LigneFacture(savedAb,nouvelleFactureWithNum,5);
			repositoryligneFacture.insertNew(ligneFacture2);
			nouvelleFactureWithNum.getLignes().add(ligneFacture2);
			
			//Facture nouvelleFacture_saved = repositoryFacture.update(nouvelleFactureWithNum); //pas indispensable car coté inverse (là où mappedBy)
			Facture nouvelleFacture_saved = nouvelleFactureWithNum;
			
			//return null; //or return something //for end of txTemplate.execute() block
			return nouvelleFacture_saved;
			//transaction and entityManager will be automatically closed at the end of this code block 
			//(instead of the end of each method invoked on a spring DAO/repository or service)
		});
		
	
		assertNotNull(factureSauvegardee);
		Long numFact=factureSauvegardee.getNumero();
		System.out.println("id/numero factureSauvegardee="+numFact);
		
		//Relecture facture sauvegardee:
		Facture fRelu = repositoryFacture.findByIdWithLines(numFact);
	     System.out.println("nouvelle facture sans cascade  : " + fRelu);
	     for(LigneFacture ligne : fRelu.getLignes()) {
	    	 System.out.println("\t" + ligne);
	     }
	     assertTrue(fRelu.getLignes().size()==2);
		
	}
	

		
}
