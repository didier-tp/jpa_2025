package tp.appliJpa.repository2;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tp.appliJpa.entity2.Article;

@SpringBootTest  
public class TestRepositoryArticle {
	
	@Autowired 
	private RepositoryArticle repositoryArticle;
	
	@Test
	public void testFindAllArticles() {
	     System.out.println("testFindAllArticles");
	     List<Article> articles = repositoryArticle.findAll();
	     for(Article a : articles) {
	    	 System.out.println("\t" + a);
	     }
	     assertTrue(articles.size()>=3);
	     
	     System.out.println("articles numeriques seulement:" + repositoryArticle.findAllArticlesNumeriques());
	     System.out.println("articles materiels seulement:" + repositoryArticle.findAllArticlesMateriels());
	   }   

}
