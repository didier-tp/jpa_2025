package tp.appliJpa.repository2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tp.appliJpa.entity2.Acteur;
import tp.appliJpa.entity2.Film;
import tp.appliJpa.entity2.RoleActeurFilm;


@SpringBootTest  
class TestRepositoryActeurFilm {
	
	@Autowired 
	private RepositoryActeur repositoryActeur;
	
	
	@Autowired 
	private RepositoryFilm repositoryFilm;

	@Autowired 	
	private RepositoryRoleActeurFilm repositoryRoleActeurFilm;
	
	
	@Test
	public void testFilmsOfActeur(){
		System.out.println("***** testFilmsOfActeur V2 *****");
		Acteur a = (Acteur) repositoryRoleActeurFilm.getActeurWithFilmsById(1);
		System.out.println("Acteur " + a.toString());
		for(RoleActeurFilm r : a.getRolesFilms())
			System.out.println("\t role " + r.getRole() + " joue dans " + r.getFilm().toString());		
	}
	
	@Test
	public void testActeursOfFilm(){
		System.out.println("***** testActeursOfFilm V2 *****");
		Film f = (Film) repositoryRoleActeurFilm.getFilmWithActorsById(1);
		System.out.println("film " + f.toString());
		for(RoleActeurFilm r : f.getRolesActeurs())
			System.out.println("\t role " + r.getRole() + " joue par " + r.getActeur().toString());
	}
	

	
	@Test
	public void testNouveauFilmPourActeur() {
		Film f = null;
		Acteur a1 = null;
				
			System.out.println("****** testNouveauFilmPourActeur (n-n = (1-n) * (1-n) JPA) *****");
			
			// sequence habituelle : new , save , getAll , maj , update , get , delete 
			
			Film nouveauFilm = new Film();
			nouveauFilm.setTitre("nom du nouveau film");
			nouveauFilm.setDate(new Date());
			nouveauFilm.setProducteur("nouveau producteur");
			nouveauFilm = repositoryFilm.insertNew(nouveauFilm);
			long num_film = nouveauFilm.getIdFilm();
			
			System.out.println("id du nouveau film: " + num_film);
			
			a1 = (Acteur) repositoryActeur.findById(1L);
			RoleActeurFilm r1 = new RoleActeurFilm("role1" , a1,nouveauFilm);
			Acteur a2 = (Acteur)  repositoryActeur.findById(2L);
			RoleActeurFilm r2 = new RoleActeurFilm("role2" , a2,nouveauFilm);
			
			repositoryRoleActeurFilm.createRoleActeurFilm(r1);
			repositoryRoleActeurFilm.createRoleActeurFilm(r2);
		
			//verif:
			
			System.out.println("liste des films de l'acteur 1 (apres insertion/sauvegarde nouveau film):");
			a1 = (Acteur)  repositoryRoleActeurFilm.getActeurWithFilmsById(1);
			for(RoleActeurFilm r : a1.getRolesFilms())
				System.out.println("\t role " + r.getRole()  + " joue dans " + r.getFilm().toString());
			
			
			System.out.println("=== nouveau film (relu en base) =====");
			f = (Film) repositoryRoleActeurFilm.getFilmWithActorsById(num_film);
			System.out.println(f);
			assertTrue(f.getIdFilm()==num_film);
			assertTrue(f.getTitre().equals("nom du nouveau film"));
			for(RoleActeurFilm rx : f.getRolesActeurs())
				System.out.println("\t role " + rx.getRole() + " joue par " + rx.getActeur().toString());
			
			//mise a jour:
			
			f.setTitre("film_xy");
			repositoryFilm.update(f);
			
			// verif mise a jour:
			
			f=(Film) repositoryFilm.findById(num_film);
			assertEquals("film_xy",f.getTitre());
			System.out.println(f.toString());
			
			System.out.println("liste des films de l'acteur 1 (apres mise a jour du film):");
			a1 = (Acteur) repositoryRoleActeurFilm.getActeurWithFilmsById(1);
			for(RoleActeurFilm ry : a1.getRolesFilms())
				System.out.println("\t role " + ry.getRole()  + " joue dans " + ry.getFilm().toString());
			
			repositoryFilm.deleteById(num_film);
			
			
			System.out.println("liste des films de l'acteur 1 (apres suppression du film):");
			a1 = (Acteur) repositoryRoleActeurFilm.getActeurWithFilmsById(1);
			//entityManager.refresh(a1);// pour que l'autre cote de la relation n-n soit au courant de la relation (cache m�moire � jour)
			for(RoleActeurFilm rz: a1.getRolesFilms())
				System.out.println("\t role " + rz.getRole()  + " joue dans " + rz.getFilm().toString());
			
			f=(Film) repositoryFilm.findById(num_film);
			assertTrue(f==null);
		
	}
}
