package tp.appliJpa.service2;


import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tp.appliJpa.entity2.Article;
import tp.appliJpa.entity2.Facture;
import tp.appliJpa.entity2.LigneFacture;
import tp.appliJpa.repository2.RepositoryArticle;
import tp.appliJpa.repository2.RepositoryFacture;
import tp.appliJpa.repository2.RepositoryLigneFacture;

@Service
@Transactional
public class ServiceFactureImpl  implements ServiceFacture{
	
	@Autowired
	private RepositoryFacture repositoryFacture;

	
	@Autowired
	private RepositoryArticle repositoryArticle;
	
	@Autowired
	private RepositoryLigneFacture repositoryLigneFacture;
	
	
	@Override
	//Version2 (avec cascades):
	
	public Facture nouvelleFactureAvecMapNouveauxArticlesQuantites(Map<Article, Integer> mapArticleQuantite) {
		//return sansCascade_nouvelleFactureAvecMapNouveauxArticlesQuantites(mapArticleQuantite);
		return avecCascade_nouvelleFactureAvecMapNouveauxArticlesQuantites(mapArticleQuantite);
	}
	
	public Facture avecCascade_nouvelleFactureAvecMapNouveauxArticlesQuantites(Map<Article, Integer> mapArticleQuantite) {
		
		//avec cascade : on créer et on rattache tout puis un seul ordre  "insertNew" :
		
		Facture nouvelleFacture = new Facture(); 
		Set<LigneFacture> lignes = nouvelleFacture.getLignes();
		
		for( Entry<Article,Integer> articleQuantiteEntry  : mapArticleQuantite.entrySet()) {
			Article article = articleQuantiteEntry.getKey();
			LigneFacture ligneFacture = new LigneFacture(article,nouvelleFacture,articleQuantiteEntry.getValue());
			lignes.add(ligneFacture);
		}
		
		return repositoryFacture.insertNew(nouvelleFacture);	
	}
	
	//Version1 (sans cascade):
	public Facture sansCascade_nouvelleFactureAvecMapNouveauxArticlesQuantites(Map<Article, Integer> mapArticleQuantite) {
		
		//Sans cascade : plein de petits ordres "insertNew" DANS LE BON ordre
		
		Facture nouvelleFacture = repositoryFacture.insertNew(new Facture()); //facture persistée une première fois sans ligne

		Set<LigneFacture> lignes = nouvelleFacture.getLignes();
		
		for( Entry<Article,Integer> articleQuantiteEntry  : mapArticleQuantite.entrySet()) {
			Article articleSauvegarde = repositoryArticle.insertNew(articleQuantiteEntry.getKey());
			LigneFacture ligneFacture = new LigneFacture(articleSauvegarde,nouvelleFacture,articleQuantiteEntry.getValue());
			LigneFacture ligneFactureSauvegardee = repositoryLigneFacture.insertNew(ligneFacture);
			//lignes.add(ligneFactureSauvegardee); //possible mais pas indispensable car coté inverse relation bi-directionnelle
		}
		
		//return repositoryFacture.update(nouvelleFacture);	//possible mais pas indispensable car coté inverse relation bi-directionnelle
		return nouvelleFacture;
	}


}
