package tp.appliJpa.entity2;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name="acteur_film")
public class RoleActeurFilm {
	
	@EmbeddedId
	private RoleActeurFilmCompositePk pk;
	
	private String role;
	
	@ManyToOne
	//@JoinColumn(name="id_acteur", insertable=false, updatable=false) //JPA >=1
	@JoinColumn(name="id_acteur")
	@MapsId("idActeur") //pk.idActeur //JPA >=2
	private Acteur acteur;
	
	@ManyToOne
	//@JoinColumn(name="id_film" , insertable=false, updatable=false) //JPA >=1
	@JoinColumn(name="id_film" )
	@MapsId("idFilm") //pk.idFilm //JPA >=2
	private Film film;
	
	
	public RoleActeurFilm() {
		super();
		pk = new RoleActeurFilmCompositePk();
	}
	
	public RoleActeurFilm(String role, Acteur acteur, Film film) {
		super();
		this.role = role;
		this.acteur = acteur;
		this.film = film;
		pk=new RoleActeurFilmCompositePk(acteur.getIdActeur(),film.getIdFilm());
		
	}
	
	public RoleActeurFilmCompositePk getPk() {
		return pk;
	}
	public void setPk(RoleActeurFilmCompositePk pk) {
		this.pk = pk;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	

	public Acteur getActeur() {
		return acteur;
	}
	public void setActeur(Acteur acteur) {
		this.acteur = acteur;
	}
	
	
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	 
	
	
}
