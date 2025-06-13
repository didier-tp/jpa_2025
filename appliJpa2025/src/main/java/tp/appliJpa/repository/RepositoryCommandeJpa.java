package tp.appliJpa.repository;



import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import tp.appliJpa.entity.Commande;
import tp.appliJpa.entity.LigneCommande;
import tp.appliJpa.entity.pk.LigneCommandePk;
import tp.appliJpa.repository.generic.RepositoryGenericJpa;



@Repository //cas particulier de @Component //pour prise en charge par framework spring
@Transactional //pour commit/rollback automatique
public class RepositoryCommandeJpa extends RepositoryGenericJpa<Commande,Integer>
               implements RepositoryCommande {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	public RepositoryCommandeJpa() {
		super(Commande.class);
	}

	@Override
	public Commande findByIdWithlines(Integer numCmde) {
		return entityManager.createNamedQuery("Commande.findByIdWithlines",Commande.class)
				.setParameter(1,  numCmde)
				.getSingleResult();
	}

	@Override
	public void deleteLigneCommande(LigneCommandePk pk) {
		entityManager.remove(entityManager.find(LigneCommande.class, pk));
	}


}
