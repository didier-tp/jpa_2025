package tp.myJpa.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_compte" , discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("CompteCourant") //par défaut (pour instnce de Compte)
 //et @DiscriminatorValue("CompteEpargne") dans sous classe CompteEpargne
public class Compte {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numero;
	
	@Length(min = 2 , message="min valid length of Compte.label is 2")
	private String label;
	
	@Min(value=-999,message="solde is too low and invalid (less than -999)")
	private Double solde;
	
	//ATTENTION, valeur de mappedBy = pas un nom de colonne 
	//, mais nom java en dessous du @ManyToOne
	//@OneToMany(fetch = FetchType.EAGER, mappedBy = "compte") //V1
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "compte") //V2
	private List<Operation> operations = new ArrayList<>(); //soit new ici, addOperation() avec if et new
	
	
	@ManyToMany
	@JoinTable(name = "client_compte",
	joinColumns = {@JoinColumn(name = "num_compte")},
	inverseJoinColumns = {@JoinColumn(name = "id_client")})
	private List<Client> clients = new ArrayList<>();//soit new ici, addClient() avec if et new
	

	//+constructeurs , get/set, .toString()


	public Compte(Long numero, String label, Double solde) {
		super();
		this.numero = numero;
		this.label = label;
		this.solde = solde;
	}

	

	@Override
	public String toString() {
		return "Compte [numero=" + numero + ", label=" + label + ", solde=" + solde + "]";
	}



	public Compte() {
		super();
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Double getSolde() {
		return solde;
	}

	public void setSolde(Double solde) {
		this.solde = solde;
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}



	public List<Client> getClients() {
		return clients;
	}



	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	
	

}
