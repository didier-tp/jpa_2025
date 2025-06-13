package tp.appliJpa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import tp.appliJpa.entity.Operation;
import tp.appliJpa.repository.generic.RepositoryGenericJpa;

@Repository   
@Transactional 
public class RepositoryOperationJpa extends RepositoryGenericJpa<Operation,Long>
                   implements RepositoryOperation {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	public RepositoryOperationJpa() {
		super(Operation.class);
	}

	@Override
	public List<Operation> findOperationsByNumCompte(long numCompte) {
		//avec @NamedQuery(... "SELECT o FROM Operation o WHERE o.compte.numero = :numCompte")
		return entityManager.createNamedQuery("Operation.findOperationsByNumCompte", 
				                             Operation.class)
				            .setParameter("numCompte", numCompte)
				            .getResultList();
	}
	
	
	
	
	
	
}