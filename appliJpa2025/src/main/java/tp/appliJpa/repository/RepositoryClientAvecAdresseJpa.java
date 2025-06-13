package tp.appliJpa.repository;



import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import tp.appliJpa.entity.BasicClient;
import tp.appliJpa.entity.ClientAvecAdresse;
import tp.appliJpa.repository.generic.RepositoryGenericJpa;

@Repository   
@Transactional 
public class RepositoryClientAvecAdresseJpa extends RepositoryGenericJpa<ClientAvecAdresse,Long>
                   implements RepositoryClientAvecAdresse {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	public RepositoryClientAvecAdresseJpa() {
		super(ClientAvecAdresse.class);
	}


	@Override
	public BasicClient getBasicClientWithoutAddress(long id) {
		final String jpqlQuery="SELECT NEW tp.appliJpa.entity.BasicClient(c.id,c.prenom,c.nom) FROM ClientAvecAdresse c WHERE c.id=?1";
		//final String jpqlQuery="SELECT NEW tp.appliJpa.entity.ClientAvecAdresse(c.id,c.prenom,c.nom) FROM ClientAvecAdresse c WHERE c.id=?1";
		return entityManager.createQuery(jpqlQuery, BasicClient.class)
				.setParameter(1, id)
				.getSingleResult();
	}
	
	
	//V2 via NativeQuery:
	public BasicClient getBasicClientWithoutAddressViaNativeQuery(long id) {
		final String nativeQuery="SELECT c.id,c.prenom,c.nom FROM Client c WHERE c.id=?1";
		/*
		return (ClientAvecAdresse) entityManager.createNativeQuery(nativeQuery, ClientAvecAdresse.class)
				.setParameter(1, id).getSingleResult();
		*/
		return (BasicClient) entityManager.createNativeQuery(nativeQuery, "BasicClientMapping")
				.setParameter(1, id).getSingleResult();
	}

	
}