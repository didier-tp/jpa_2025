package tp.appliJpa.repository2;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tp.appliJpa.entity2.Article;
import tp.appliJpa.entity2.Facture;
import tp.appliJpa.entity2.LigneFacture;
import tp.appliJpa.service2.ServiceFacture;


@SpringBootTest  
class TestRepositoryFactureAvecCascade {
	
	@Autowired 
	private RepositoryFacture repositoryFacture;
	
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
	
	@Test
	public void testAjoutFactureNouveauxArticles() {
		
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
	

		
}
