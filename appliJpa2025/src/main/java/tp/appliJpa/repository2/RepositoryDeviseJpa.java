package tp.appliJpa.repository2;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import tp.appliJpa.entity2.Devise;
import tp.appliJpa.repository.generic.RepositoryGenericJpa;


@Repository
@Transactional
public class RepositoryDeviseJpa extends RepositoryGenericJpa<Devise, String> implements RepositoryDevise {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	public RepositoryDeviseJpa() {
		super(Devise.class);
	}

	@Override
	public Devise findDeviseByName(String name) {
		return entityManager.createQuery("SELECT d FROM Devise d WHERE d.monnaie= :name",Devise.class)
				.setParameter("name", name)
				.getSingleResult();
	}

}