package tp.appliJpa.service2;

import java.util.Map;

import tp.appliJpa.entity2.Article;
import tp.appliJpa.entity2.Facture;

public interface ServiceFacture {
	
	Facture nouvelleFactureAvecMapNouveauxArticlesQuantites(Map<Article,Integer> mapArticleQuantite) ;

}
