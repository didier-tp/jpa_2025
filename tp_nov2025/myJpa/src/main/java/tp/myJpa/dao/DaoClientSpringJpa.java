package tp.myJpa.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import tp.myJpa.dao.generic.DaoGenericJpa;
import tp.myJpa.entity.Client;

@Repository
@Transactional
public class DaoClientSpringJpa extends DaoGenericJpa<Client,Long> implements DaoClient {
	
	@PersistenceContext
	private EntityManager entityManager;

	public DaoClientSpringJpa() {
		super(Client.class);
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public Client findByIdWithComptes(Long idClient) {
		return entityManager.createQuery("select c from Client c left join FETCH c.comptes where c.id = :idClient",Client.class)
				.setParameter("idClient", idClient)
				.getSingleResult();
	}
	
	//+code d'éventuels .find... spécifiques



}









