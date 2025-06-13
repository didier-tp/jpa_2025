package tp.appliJpa.repository2;

import tp.appliJpa.entity2.Acteur;
import tp.appliJpa.entity2.Film;
import tp.appliJpa.entity2.RoleActeurFilm;
import tp.appliJpa.entity2.RoleActeurFilmCompositePk;

public interface RepositoryRoleActeurFilm {
	
	public RoleActeurFilmCompositePk createRoleActeurFilm(RoleActeurFilm roleActeurFilm);// return pk
	public RoleActeurFilm update(RoleActeurFilm roleActeurFilm);
	public void deleteRoleActeurFilm(long idActeur,long idFilm);

	public void attachActorToFilm(Film f, Acteur a);
	public Film getFilmWithActorsById(long idFilm);
	public Acteur getActeurWithFilmsById(long idActeur);
	
}
