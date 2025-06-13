package tp.appliJpa.repository2;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import tp.appliJpa.entity2.Article;
import tp.appliJpa.repository.generic.RepositoryGenericJpa;

@Repository
@Transactional
public class RepositoryArticleJpa extends RepositoryGenericJpa<Article, Long> implements RepositoryArticle {

		@PersistenceContext
		private EntityManager entityManager;

		@Override
		public EntityManager getEntityManager() {
			return this.entityManager;
		}

		public RepositoryArticleJpa() {
			super(Article.class);
		}
}
