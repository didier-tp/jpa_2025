package tp.appliJpa.entity2;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import tp.appliJpa.entity.Client;

@Entity
@Table(name="facture")
public class Facture {
	
	@Id @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long numero;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	/*
	//IMPROVED VERSION WITH client:
	@ManyToOne( cascade = CascadeType.PERSIST ) 
	@JoinColumn( name = "id_client", nullable = false )
	private Client client;
	*/
	
	//@OneToMany( mappedBy = "facture", fetch = FetchType.LAZY)
	@OneToMany( mappedBy = "facture", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })  //CASCADE UTILE/IMPORTANTE
	private Set<LigneFacture> lignes ;
	
	public Facture(Date date) {
		this(date,new HashSet<LigneFacture>());
	}
	
	public Facture() {
		this(new Date());
	}

	public Facture(Date date, Set<LigneFacture> lignes) {
		super();
		this.numero = null;
		this.date = date;
		this.lignes = lignes;
	}
	
	

	@Override
	public String toString() {
		return "Facture [numero=" + numero + ", date=" + date + "]";
	}



	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Set<LigneFacture> getLignes() {
		return lignes;
	}

	public void setLignes(Set<LigneFacture> lignes) {
		this.lignes = lignes;
	}
	
	
	

}
