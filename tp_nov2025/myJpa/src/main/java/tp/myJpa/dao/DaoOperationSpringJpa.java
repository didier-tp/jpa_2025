package tp.myJpa.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import tp.myJpa.dao.generic.DaoGenericJpa;
import tp.myJpa.entity.Operation;

@Repository
@Transactional
public class DaoOperationSpringJpa extends DaoGenericJpa<Operation,Long> implements DaoOperation {
	
	@PersistenceContext
	private EntityManager entityManager;

	public DaoOperationSpringJpa() {
		super(Operation.class);
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	//+code d'éventuels .find... spécifiques

}
