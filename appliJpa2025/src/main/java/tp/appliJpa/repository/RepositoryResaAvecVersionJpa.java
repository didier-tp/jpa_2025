package tp.appliJpa.repository;



import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import tp.appliJpa.entity.ResaAvecVersion;
import tp.appliJpa.repository.generic.RepositoryGenericJpa;

@Repository   
@Transactional 
public class RepositoryResaAvecVersionJpa extends RepositoryGenericJpa<ResaAvecVersion,Long>
                   implements RepositoryResaAvecVersion {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	public RepositoryResaAvecVersionJpa() {
		super(ResaAvecVersion.class);
	}

	
}