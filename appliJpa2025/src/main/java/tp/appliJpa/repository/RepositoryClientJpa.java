package tp.appliJpa.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import tp.appliJpa.dto.ClientEssential;
import tp.appliJpa.entity.Client;
import tp.appliJpa.repository.generic.RepositoryGenericJpa;

@Repository   
@Transactional 
public class RepositoryClientJpa extends RepositoryGenericJpa<Client,Long>
                   implements RepositoryClient {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	public RepositoryClientJpa() {
		super(Client.class);
	}

	//version with JPQL and entityGraph Hint/property
	public Client clientAvecComptesV2(Long idClient) {
		EntityGraph entityGraphClientComptes = entityManager.getEntityGraph("entity-graph-client-comptes");
		Client clientAvecComptes = entityManager.createQuery("select c from Client c where c.id = ?1", Client.class)
				  .setParameter(1, idClient)
				  .setHint("javax.persistence.fetchgraph", entityGraphClientComptes)//ou jakarta.persistence.fetchgraph
				  .getSingleResult();
		return clientAvecComptes;
	}
	
	//Basic version with entityManager.find() with entityGraph properties
	public Client clientAvecComptes(Long idClient) {
	//public Client clientAvecComptesV1(Long idClient) {
		EntityGraph entityGraphClientComptes = entityManager.getEntityGraph("entity-graph-client-comptes");
		Map<String, Object> properties = new HashMap<>();
		properties.put("javax.persistence.fetchgraph", entityGraphClientComptes);//ou jakarta.persistence.fetchgraph
		Client clientAvecComptes = entityManager.find(Client.class, idClient, properties);
		//Client clientSansComptes = entityManager.find(Client.class, idClient);//with future lazyException
		return clientAvecComptes;
	}
	
	//version avec complexe api criteria (pour les puristes du typage fort)
	public Client clientAvecComptesV3(Long idClient) {
			EntityGraph entityGraphClientComptes = entityManager.getEntityGraph("entity-graph-client-comptes");
			
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Client> criteriaQuery = criteriaBuilder.createQuery(Client.class);
			Root<Client> root = criteriaQuery.from(Client.class);
			criteriaQuery.where(criteriaBuilder.equal(root.<Long>get("id"), idClient));
			TypedQuery<Client> typedQuery = entityManager.createQuery(criteriaQuery);
			typedQuery.setHint("javax.persistence.loadgraph", entityGraphClientComptes);//ou jakarta.persistence.fetchgraph
			Client clientAvecComptes = typedQuery.getSingleResult();

			return clientAvecComptes;
		}

	@Override
	public Client clientAvecComptesEtOperations(Long idClient) {
		// bug or limitation : EntityGraph may return NO DISTINCT Values with many-to-many relationships !!!!
		EntityGraph entityGraphClientComptes = entityManager.getEntityGraph("entity-graph-client-comptes-operations");
		Map<String, Object> properties = new HashMap<>();
		properties.put("javax.persistence.loadgraph", entityGraphClientComptes);//ou jakarta.persistence.loadgraph
		Client clientAvecComptes = entityManager.find(Client.class, idClient, properties);
		//WORKAROUND (many-to-many NO DISTINCT ) :
		clientAvecComptes.setComptes(clientAvecComptes.getComptes().stream().distinct().toList());
        //OK en TP mais ATTENTION aux performances si beaucoup de données
		//donc avoir en tête que jpa/hibernate semble mieux gèrer le @OneToMany que le @ManyToMany avec les entityGraph 
		return clientAvecComptes;
	}

	
	//EXEMPLE DE PROJECTION JPA:
	
	@Override
	public ClientEssential getClientEssentialFromClientId(Long idClient) {
		String jpqlQueryWithProjection = "SELECT new tp.appliJpa.dto.ClientEssential(c.prenom,c.nom,SIZE(c.comptes)) " 
	                                     + " FROM Client c WHERE c.id= :id";
		//NB: SIZE() function of JPA2 return the size of a java collection
		ClientEssential clientEssential = entityManager.createQuery(jpqlQueryWithProjection,ClientEssential.class).setParameter("id",idClient).getSingleResult();
		return clientEssential;
	}

	
}