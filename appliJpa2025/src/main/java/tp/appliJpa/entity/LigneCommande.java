package tp.appliJpa.entity;



import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import tp.appliJpa.entity.pk.LigneCommandePk;



@Entity
@Table(name = "ligne_commande")
public class LigneCommande {
	
	@EmbeddedId
	private LigneCommandePk pk;
	
	@Column(name="ref_produit")
	private String refProduit;
	
	private Integer quantite;
	
	@Column(name="prix_unitaire")
	private Double prixUnitaire;
	
	@ManyToOne
	@JoinColumn(name="num_commande" , insertable=false, updatable=false)
	private Commande commande;

	public LigneCommande() {
		super();
	}
	
	public LigneCommande( String refProduit, Integer quantite, Double prixUnitaire) {
		this(new LigneCommandePk(),refProduit,quantite,prixUnitaire);
	}

	public LigneCommande(LigneCommandePk pk, String refProduit, Integer quantite, Double prixUnitaire) {
		super();
		this.pk = pk;
		this.refProduit = refProduit;
		this.quantite = quantite;
		this.prixUnitaire = prixUnitaire;
	}

	@Override
	public String toString() {
		return "LigneCommande [pk=" + pk + ", refProduit=" + refProduit + ", quantite=" + quantite + ", prixUnitaire="
				+ prixUnitaire + "]";
	}

	public LigneCommandePk getPk() {
		return pk;
	}

	public void setPk(LigneCommandePk pk) {
		this.pk = pk;
	}

	public String getRefProduit() {
		return refProduit;
	}

	public void setRefProduit(String refProduit) {
		this.refProduit = refProduit;
	}

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	public Double getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(Double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}
	
	

}
