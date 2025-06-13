package tp.appliJpa.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

//avec strategie singleTable



@Entity
@DiscriminatorValue("CompteEpargne")
public class CompteEpargne extends Compte {
    private Double taux;

	public Double getTaux() {
		return taux;
	}

	public void setTaux(Double taux) {
		this.taux = taux;
	}
	
	

	public CompteEpargne() {
		super();
	}

	public CompteEpargne(Long numero, String label, Double solde , Double taux) {
		super(numero, label, solde);
		this.taux=taux;
	}

	@Override
	public String toString() {
		return "CompteEpargne [taux=" + taux + ", toString()=" + super.toString() + "]";
	}

	
}
