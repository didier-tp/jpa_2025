package tp.myJpa.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Operation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numOp;
	
	private String label;
	
	private Double montant;
	
	@Temporal(TemporalType.DATE) //par défaut au format "yyyy-mm-dd"
	private Date dateOp;
	
	@ManyToOne
	@JoinColumn(name="num_compte")
	private Compte compte;
	
	//+constructeurs , get/set, .toString()
	@Override
	public String toString() {
		return "Operation [numOp=" + numOp + ", label=" + label + ", montant=" + montant + ", dateOp=" + dateOp + "]";
	}
	

	public Operation() {
		super();
	}

	public Operation(Long numOp, String label, Double montant) {
		super();
		this.numOp = numOp;
		this.label = label;
		this.montant = montant;
		this.dateOp = new Date();
	}


	public Long getNumOp() {
		return numOp;
	}


	public void setNumOp(Long numOp) {
		this.numOp = numOp;
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


	public Double getMontant() {
		return montant;
	}


	public void setMontant(Double montant) {
		this.montant = montant;
	}


	public Date getDateOp() {
		return dateOp;
	}


	public void setDateOp(Date dateOp) {
		this.dateOp = dateOp;
	}


	public Compte getCompte() {
		return compte;
	}


	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	
	
	
	
	
}
