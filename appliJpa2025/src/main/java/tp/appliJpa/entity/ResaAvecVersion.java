package tp.appliJpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name="resa_avec_version")
public class ResaAvecVersion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_resa")
	private Long idResa;
	
	@Version //pour verrouillage optimiste
	@Column(name="num_version")
	private Long numVersion; //géré par JPA/Hibernate , en lecture seulement
	
	private String objet;//ex: "uniqueVoitureDeCourtoisie";
	
	@Column(name="num_client")
	private Long numClient=null; //numero du client qui peut utiliser la voiture cette semaine

	
	
	
	public ResaAvecVersion() {
		super();
	}

	public ResaAvecVersion(Long idResa,String objet, Long numClient) {
		super();
		this.idResa = idResa;
		this.objet = objet;
		this.numClient = numClient;
	}

	@Override
	public String toString() {
		return "ResaAvecVersion [idResa=" + idResa + ", numVersion=" + numVersion + ", objet=" + objet + ", numClient="
				+ numClient + "]";
	}

	public Long getIdResa() {
		return idResa;
	}

	public void setIdResa(Long idResa) {
		this.idResa = idResa;
	}

	public Long getNumVersion() {
		return numVersion;
	}


	public String getObjet() {
		return objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}

	public Long getNumClient() {
		return numClient;
	}

	public void setNumClient(Long numClient) {
		this.numClient = numClient;
	}
	
	

}
