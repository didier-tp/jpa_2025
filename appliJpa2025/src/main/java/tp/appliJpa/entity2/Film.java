package tp.appliJpa.entity2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

//relation n-n avec infos supplementaire dans table de jointure
@Entity
@Table(name="film")
public class Film {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_film")
	private Long idFilm; //default column name (snake case): id_film
	
	private String titre;
	
	@Column(name="date_sortie")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	private String producteur;
	
	@OneToMany(mappedBy="film")
	private List<RoleActeurFilm> rolesActeurs = new ArrayList<>();
	
	public Long getIdFilm() {
		return idFilm;
	}
	public void setIdFilm(Long idFilm) {
		this.idFilm = idFilm;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String nom) {
		this.titre = nom;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getProducteur() {
		return producteur;
	}
	public void setProducteur(String producteur) {
		this.producteur = producteur;
	}
	@Override
	public String toString() {
		return "[" + idFilm+ "] " + this.getTitre() + " - "+ this.getProducteur();
	}
	
	
	public List<RoleActeurFilm> getRolesActeurs() {
		return rolesActeurs;
	}

	public void setRolesActeurs(List<RoleActeurFilm> rolesActeurs) {
		this.rolesActeurs = rolesActeurs;
	}

}
