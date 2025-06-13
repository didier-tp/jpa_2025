package tp.appliJpa.entity2;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="acteur")
public class Acteur  {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_acteur")
	private Long idActeur;//default column name (snake_case): id_acteur
	
	private String nom;
	//private String prenom;
	
	@Override
	public String toString() {
		return "[" + idActeur+ "] " + " "+ this.getNom();
	}

	@OneToMany(mappedBy="acteur")
	private List<RoleActeurFilm> rolesFilms  = new ArrayList<>();;
	
	
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public Long getIdActeur() {
		return idActeur;
	}

	public void setIdActeur(Long idActeur) {
		this.idActeur = idActeur;
	}


	
	public List<RoleActeurFilm> getRolesFilms() {
		return rolesFilms;
	}

	public void setRolesFilms(List<RoleActeurFilm> rolesFilms) {
		this.rolesFilms = rolesFilms;
	}
	
	
	
}
