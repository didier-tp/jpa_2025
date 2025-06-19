package tp.appliJpa.repository2;

import java.util.List;

import tp.appliJpa.entity2.Article;
import tp.appliJpa.entity2.ArticleMateriel;
import tp.appliJpa.entity2.ArticleNumerique;
import tp.appliJpa.repository.generic.RepositoryGeneric;

public interface RepositoryArticle extends RepositoryGeneric<Article,Long>{
	List<ArticleNumerique> findAllArticlesNumeriques();
	List<ArticleMateriel> findAllArticlesMateriels();
}