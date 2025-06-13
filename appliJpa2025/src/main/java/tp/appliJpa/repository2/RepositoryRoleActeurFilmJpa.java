package tp.appliJpa.repository2;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import tp.appliJpa.entity2.Acteur;
import tp.appliJpa.entity2.Film;
import tp.appliJpa.entity2.RoleActeurFilm;
import tp.appliJpa.entity2.RoleActeurFilmCompositePk;
import tp.appliJpa.repository.generic.RepositoryGenericJpa;



@Repository
@Transactional
public class RepositoryRoleActeurFilmJpa extends RepositoryGenericJpa<RoleActeurFilm, RoleActeurFilmCompositePk> implements RepositoryRoleActeurFilm {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	public RepositoryRoleActeurFilmJpa() {
		super(RoleActeurFilm.class);
	}

	@Override
	public RoleActeurFilmCompositePk createRoleActeurFilm(RoleActeurFilm roleActeurFilm) {
		RoleActeurFilm savedRoleActeurFilm =  this.insertNew(roleActeurFilm);
		return roleActeurFilm.getPk();
	};

	@Override
	public void deleteRoleActeurFilm(long idActeur, long idFilm) {
		RoleActeurFilm r  = (RoleActeurFilm) 
			     entityManager.find(RoleActeurFilm.class,
	    		            new RoleActeurFilmCompositePk(idActeur, idFilm));
		entityManager.remove(r); 
	}

	@Override
	public void attachActorToFilm(Film f, Acteur a) {
		Acteur pa = entityManager.find(Acteur.class, a.getIdActeur());
		Film pf = entityManager.find(Film.class, f.getIdFilm());
		RoleActeurFilm raf = new RoleActeurFilm("role_inconnu" , pa,pf);
		entityManager.persist(raf); //a peaufiner
		
	}

	@Override
	public Film getFilmWithActorsById(long idFilm) {
		Film film = entityManager.find(Film.class, idFilm);
		for(RoleActeurFilm raf : film.getRolesActeurs()){
			raf.getActeur();
		}
		return film;
	}

	@Override
	public Acteur getActeurWithFilmsById(long idActeur) {
		Acteur a = entityManager.find(Acteur.class, idActeur);
		for(RoleActeurFilm raf : a.getRolesFilms()){
			raf.getFilm();
		}
		return a;
	}

}