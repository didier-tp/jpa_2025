package tp.appliJpa.repository2;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import tp.appliJpa.entity2.Acteur;
import tp.appliJpa.repository.generic.RepositoryGenericJpa;

@Repository
@Transactional
public class RepositoryActeurJpa extends RepositoryGenericJpa<Acteur, Long> implements RepositoryActeur {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	public RepositoryActeurJpa() {
		super(Acteur.class);
	}

}
