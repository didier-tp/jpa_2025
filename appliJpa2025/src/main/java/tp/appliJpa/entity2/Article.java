package tp.appliJpa.entity2;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name="article")
@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "type_article", discriminatorType = DiscriminatorType.STRING)
//@DiscriminatorValue("Article")
//NB: discriminator column not mandatory for strategy = InheritanceType.JOINED , not activated here, just for performance boost
public class Article {
	
	@Id @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	
	private String label;
	private Double prix;
	
	
	public Article(Long id, String label, Double prix) {
		super();
		this.id = id;
		this.label = label;
		this.prix = prix;
	}
	
	public Article() {
		this(null,null,null);
	}
	
	
	@Override
	public String toString() {
		return "Article [id=" + id + ", label=" + label + ", prix=" + prix + "]";
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Double getPrix() {
		return prix;
	}
	public void setPrix(Double prix) {
		this.prix = prix;
	}
	
	

}
