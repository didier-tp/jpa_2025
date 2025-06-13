package tp.appliJpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="adresse_de_client")
public class AdresseClient {
	
	@Id
	//@Column(name = "id_client") column name already set by @MapsId and @JoinColumn
	private Long idClient;
	
	@OneToOne(optional=false)
	@MapsId//@MapsId is better than @PrimaryKeyJoinColumn
	@JoinColumn(name = "id_client")
	private ClientAvecAdresse client;
	
	private String numero;//ex: 2 ou "2bis"
	private String rue;
	
	@Column(name="code_postal")
	private String codePostal;
	
	private String ville;
	
	public AdresseClient() {
		super();
	}
	
	public AdresseClient( String numero, String rue, String codePostal, String ville, Long idClient) {
		super();
		this.numero = numero;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.idClient = idClient;
	}
	
	public AdresseClient( String numero, String rue, String codePostal, String ville) {
		this(numero , rue, codePostal,ville, null);
	}
	
	
	@Override
	public String toString() {
		return "Adresse [idClient=" + idClient + ", numero=" + numero + ", rue=" + rue + ", codePostal=" + codePostal
				+ ", ville=" + ville + "]";
	}
	public Long getIdClient() {
		return idClient;
	}
	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}

	public ClientAvecAdresse getClient() {
		return client;
	}

	public void setClient(ClientAvecAdresse client) {
		this.client = client;
	}
	
	
}
