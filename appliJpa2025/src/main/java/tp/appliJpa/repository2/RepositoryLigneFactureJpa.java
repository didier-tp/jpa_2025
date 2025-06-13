package tp.appliJpa.repository2;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import tp.appliJpa.entity2.LigneFacture;
import tp.appliJpa.repository.generic.RepositoryGenericJpa;

@Repository
@Transactional
public class RepositoryLigneFactureJpa extends RepositoryGenericJpa<LigneFacture, Long> implements RepositoryLigneFacture {

		@PersistenceContext
		private EntityManager entityManager;

		@Override
		public EntityManager getEntityManager() {
			return this.entityManager;
		}

		public RepositoryLigneFactureJpa() {
			super(LigneFacture.class);
		}
}