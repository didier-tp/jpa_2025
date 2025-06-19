package tp.appliJpa.entity2;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="facture")
@SecondaryTable(name = "client_infos_facture" , 
                pkJoinColumns = @PrimaryKeyJoinColumn( name="num_facture", referencedColumnName = "numero")) 
             //NB: si pas pkJoinColumns , par défaut même nom de colonne pour "pk" dans table secondaire que dans table primaire (ici "numero")
public class Facture {
	
	@Id @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long numero;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	
	//NB: idéalement , lien @ManyToOne vers entité Client
	// ici , exceptionellement, mapping sur plusieurs tables : facture et client_infos_facture 
	// avec correspondance implicite de clef primaire entres tables secondaires et table principale
	
	@Column(name = "nom", table = "client_infos_facture")
	private String nom_client = "client_lie_a_la_facture";
	
	@Column(name = "adresse", table = "client_infos_facture")
	private String adresse_client = "numero , rue xyz codePostal VilleQuiVaBien";
	
	@Column(name = "email", table = "client_infos_facture")
	private String email_client = "clientQuiVaBien@iciOuLa.fr";
	
	/*
	//IMPROVED VERSION WITH client as separated entity :
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
		return "Facture [numero=" + numero + ", date=" + date + ", nom_client=" + nom_client + ", adresse_client="
				+ adresse_client + ", email_client=" + email_client + "]";
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

	public String getNom_client() {
		return nom_client;
	}

	public void setNom_client(String nom_client) {
		this.nom_client = nom_client;
	}

	public String getAdresse_client() {
		return adresse_client;
	}

	public void setAdresse_client(String adresse_client) {
		this.adresse_client = adresse_client;
	}

	public String getEmail_client() {
		return email_client;
	}

	public void setEmail_client(String email_client) {
		this.email_client = email_client;
	}
	
	
	

}
