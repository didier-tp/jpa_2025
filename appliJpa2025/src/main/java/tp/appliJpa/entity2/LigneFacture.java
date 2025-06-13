package tp.appliJpa.entity2;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="ligne_facture")
public class LigneFacture {
	
	@Id @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	
	private Integer quantite;
	
	@ManyToOne( )
	//@ManyToOne( cascade = CascadeType.PERSIST )  //CASCADE POSSIBLE/MINEURE
    @JoinColumn( name = "id_facture", nullable = false )
	private Facture facture;
	
	//@ManyToOne( ) 
	@ManyToOne( cascade = CascadeType.PERSIST )  //CASCADE UTILE/IMPORTANTE (ici , pas de CASCADE sur REMOVE !!!!)
    @JoinColumn( name = "id_article", nullable = false )
	private Article article;

	public LigneFacture(Article article , Facture facture , Integer quantite) {
		this.id = null;
		this.quantite = quantite;
		this.facture=facture;
		this.article = article;
	}
	
	public LigneFacture(Article article , Facture facture ) {
		this(article,facture,1);
	}
	
	public LigneFacture() {
		this(null,null);
	}

	@Override
	public String toString() {
		return "LigneFacture [id=" + id + ", quantite=" + quantite + ", article=" + article + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	public Facture getFacture() {
		return facture;
	}

	public void setFacture(Facture facture) {
		this.facture = facture;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
	
	
	

}
