package tp.appliJpa.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapKey;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;



@Entity
@Table(name = "commande")
@NamedQueries({
	@NamedQuery(name="Commande.findByIdWithlines", 
			    query = "SELECT c FROM Commande c inner join fetch c.lignes WHERE c.numero = ?1")
})
public class Commande {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer numero;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@OneToMany(mappedBy="commande", cascade = { CascadeType.REMOVE , CascadeType.MERGE } )
	//private List<LigneCommande> lignes; //V1
	@MapKey(name="pk.numLigne")
	private Map<Integer,LigneCommande> lignes; //V2
	
	public int getNextNumLigne() {
		int maxNumLigne =0;
		//V1:for(LigneCommande ligne : this.lignes) {
		for(LigneCommande ligne : this.lignes.values()) {
			if(ligne.getPk().getNumLigne()>=maxNumLigne)
				maxNumLigne=ligne.getPk().getNumLigne();
		}
		return (maxNumLigne+1);
	}
	
	public LigneCommande addLigneCommande(LigneCommande ligne) {
		if(this.lignes==null) 
			lignes = new HashMap<Integer,LigneCommande>();
		if(ligne.getPk().getNumCommande()==null) 
			ligne.getPk().setNumCommande(this.numero);
		if(ligne.getPk().getNumLigne()==null) 
			ligne.getPk().setNumLigne(this.getNextNumLigne());
		this.lignes.put(ligne.getPk().getNumLigne(),ligne);
		return ligne;
	}
	
	public LigneCommande removeLigneCommande(LigneCommande ligne) {
		lignes.remove(ligne.getPk().getNumLigne());
		return ligne;
	}

	public Commande() {
		super();
	}



	public Commande(Integer numero, Date date) {
		super();
		this.numero = numero;
		this.date = date;
	}
	
	

	@Override
	public String toString() {
		return "Commande [numero=" + numero + ", date=" + date  + "]";
	}



	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Map<Integer, LigneCommande> getLignes() {
		return lignes;
	}

	public void setLignes(Map<Integer, LigneCommande> lignes) {
		this.lignes = lignes;
	}

	

	/*
	//V1:
	public List<LigneCommande> getLignes() {
		return lignes;
	}

	public void setLignes(List<LigneCommande> lignes) {
		this.lignes = lignes;
	}
	
	*/
	

}
