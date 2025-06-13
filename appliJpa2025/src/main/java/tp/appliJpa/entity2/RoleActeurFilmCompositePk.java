package tp.appliJpa.entity2;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;



@Embeddable
public class RoleActeurFilmCompositePk implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="id_acteur")
	private Long idActeur;
	
	@Column(name="id_film")
	private Long idFilm; 
	
	public RoleActeurFilmCompositePk() {
		super();
	}
	public RoleActeurFilmCompositePk(Long idActeur, Long idFilm) {
		super();
		this.idActeur = idActeur;
		this.idFilm = idFilm;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idActeur ^ (idActeur >>> 32));
		result = prime * result + (int) (idFilm ^ (idFilm >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final RoleActeurFilmCompositePk other = (RoleActeurFilmCompositePk) obj;
		if (idActeur != other.idActeur)
			return false;
		if (idFilm != other.idFilm)
			return false;
		return true;
	}
	public Long getIdActeur() {
		return idActeur;
	}
	public void setIdActeur(Long idActeur) {
		this.idActeur = idActeur;
	}
	public Long getIdFilm() {
		return idFilm;
	}
	public void setIdFilm(Long idFilm) {
		this.idFilm = idFilm;
	}
	
	
}
