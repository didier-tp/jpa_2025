package tp.appliJpa.repository;



import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import tp.appliJpa.entity.BigData;
import tp.appliJpa.repository.generic.RepositoryGenericJpa;

@Repository   
@Transactional 
public class RepositoryBigDataJpa extends RepositoryGenericJpa<BigData,Long>
                   implements RepositoryBigData {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	public RepositoryBigDataJpa() {
		super(BigData.class);
	}

	
}