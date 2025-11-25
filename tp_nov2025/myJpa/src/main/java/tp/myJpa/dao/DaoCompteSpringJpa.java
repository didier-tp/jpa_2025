package tp.myJpa.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import tp.myJpa.dao.generic.DaoGenericJpa;
import tp.myJpa.entity.Compte;

@Repository
@Transactional
public class DaoCompteSpringJpa extends DaoGenericJpa<Compte,Long> implements DaoCompte {
	
	@PersistenceContext
	private EntityManager entityManager;

	public DaoCompteSpringJpa() {
		super(Compte.class);
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	//+code d'éventuels .find... spécifiques

}
