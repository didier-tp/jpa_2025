package tp.appliJpa.repository2;

import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import tp.appliJpa.entity2.Article;
import tp.appliJpa.entity2.Facture;
import tp.appliJpa.repository.generic.RepositoryGenericJpa;


@Repository
@Transactional
public class RepositoryFactureJpa extends RepositoryGenericJpa<Facture, Long> implements RepositoryFacture {

		@PersistenceContext
		private EntityManager entityManager;

		@Override
		public EntityManager getEntityManager() {
			return this.entityManager;
		}

		public RepositoryFactureJpa() {
			super(Facture.class);
		}

		@Override
		public Facture findByIdWithLines(Long numFacture) {
			String jpqlQuery="SELECT f FROM Facture f LEFT JOIN FETCH f.lignes WHERE f.numero=:numFacture";
			return this.entityManager.createQuery(jpqlQuery,Facture.class)
					    .setParameter("numFacture", numFacture).getSingleResult();
		}

}