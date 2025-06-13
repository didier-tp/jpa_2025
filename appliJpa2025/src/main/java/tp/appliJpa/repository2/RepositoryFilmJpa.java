package tp.appliJpa.repository2;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import tp.appliJpa.entity2.Film;
import tp.appliJpa.entity2.RoleActeurFilm;
import tp.appliJpa.repository.generic.RepositoryGenericJpa;

@Repository
@Transactional
public class RepositoryFilmJpa extends RepositoryGenericJpa<Film, Long> implements RepositoryFilm {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	public RepositoryFilmJpa() {
		super(Film.class);
	}

	@Override
	public void deleteById(Long id) {
		Film film  = (Film) this.findById(id);
		
		// Attention , il faut d'abord supprimer les RolesActeurs avant de  supprimer le film 
		// (==> sinon : pb contrainte integrite referentielle)
		for(RoleActeurFilm raf : film.getRolesActeurs())
						entityManager.remove(raf);
		entityManager.flush();// pour bien controler l'ordre
					
		entityManager.remove(film); 
	}
	
	

}