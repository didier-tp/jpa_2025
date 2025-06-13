package tp.appliJpa.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;



@Entity
@NamedQuery(name="Operation.findOperationsByNumCompte" , 
     query="SELECT o FROM Operation o WHERE o.compte.numero = :numCompte")
public class Operation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="num_op")
	private Long  numOp;
	
	private String label; //ex: "achat bonbon" ou "paye du mois"
	
	private Double montant; //ex: -5.5 ou +2000
	
	@Temporal(TemporalType.DATE)
	@Column(name="date_op")
	private Date dateOp; //ex: 2022-12-08 yyyy-MM-dd

    //+lien @ManyToOne de Operation vers compte
    //référence "compte" référençant un objet de type "Compte"
	@ManyToOne
	@JoinColumn(name="num_compte") //num_compte=nom colonne clef etrangère (fk)
	private Compte compte;
	
	

	public Operation() {
		super();
	}

	public Operation(Long numOp, String label, Double montant, Date dateOp, Compte compte) {
		super();
		this.numOp = numOp;
		this.label = label;
		this.montant = montant;
		this.dateOp = dateOp;
		this.compte = compte;
	}
	
	public Operation(Long numOp, String label, Double montant) {
		super();
		this.numOp = numOp;
		this.label = label;
		this.montant = montant;
		this.dateOp = new Date();
		this.compte = null;
	}

	@Override
	public String toString() {
		return "Operation [numOp=" + numOp + ", label=" + label + ", montant=" + montant + ", dateOp=" + dateOp
				+ ", compte=" + compte + "]";
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
