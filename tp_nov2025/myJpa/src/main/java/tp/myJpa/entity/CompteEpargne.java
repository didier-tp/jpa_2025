package tp.myJpa.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CompteEpargne")
public class CompteEpargne extends Compte {
	
	private Double taux; //+get/set , constructeur(s) , .toString()


	public CompteEpargne(Long numero, String label, Double solde, Double taux) {
		super(numero, label, solde);
		this.taux=taux;
	}

	public CompteEpargne() {
		super();
	}

	@Override
	public String toString() {
		return super.toString() + " CompteEpargne [taux=" + taux + "]";
	}

	public Double getTaux() {
		return taux;
	}

	public void setTaux(Double taux) {
		this.taux = taux;
	}

	
}
