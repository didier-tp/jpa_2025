package tp.appliJpa.entity2;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="article_materiel") //table complémentaire vis à vis de table article
public class ArticleMateriel extends Article{
	private Double poids; //en kg
	private Double volume; //em m3
	
	public ArticleMateriel(Long id, String label, Double prix, Double poids, Double volume) {
		super(id, label, prix);
		this.poids = poids;
		this.volume = volume;
	}
	
	public ArticleMateriel() {
		super();
	}

	public ArticleMateriel(Long id, String label, Double prix) {
		super(id, label, prix);
	}


	@Override
	public String toString() {
		return "ArticleMateriel [poids=" + poids + ", volume=" + volume + ", toString()=" + super.toString() + "]";
	}
	public Double getPoids() {
		return poids;
	}
	public void setPoids(Double poids) {
		this.poids = poids;
	}
	public Double getVolume() {
		return volume;
	}
	public void setVolume(Double volume) {
		this.volume = volume;
	}
	
	
}
