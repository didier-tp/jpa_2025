package tp.appliJpa.entity.pk;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;



@Embeddable
public class LigneCommandePk implements Serializable {
	@Column(name="num_commande")
	private Integer numCommande;
	
	@Column(name="num_ligne")
	private Integer numLigne;
	
	public LigneCommandePk() {
		super();
	}

	public LigneCommandePk(Integer numCommande, Integer numLigne) {
		super();
		this.numCommande = numCommande;
		this.numLigne = numLigne;
	}
	
	@Override
	public String toString() {
		return "LigneCommandePk [numCommande=" + numCommande + ", numLigne=" + numLigne + "]";
	}
	public Integer getNumCommande() {
		return numCommande;
	}
	public void setNumCommande(Integer numCommande) {
		this.numCommande = numCommande;
	}
	public Integer getNumLigne() {
		return numLigne;
	}
	public void setNumLigne(Integer numLigne) {
		this.numLigne = numLigne;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numCommande, numLigne);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LigneCommandePk other = (LigneCommandePk) obj;
		return Objects.equals(numCommande, other.numCommande) && Objects.equals(numLigne, other.numLigne);
	}
	
	
	
	
}
