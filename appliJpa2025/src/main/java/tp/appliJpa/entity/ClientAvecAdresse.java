package tp.appliJpa.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;



@Entity
@Table(name="client")
public class ClientAvecAdresse extends BasicClient{

	@OneToOne(optional=true,
			  mappedBy="client",
			  cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	private AdresseClient adresse;//may be null if not known or not specified or not attached
	
	
	public ClientAvecAdresse() {
		super();
	}


	public ClientAvecAdresse(Long id, String prenom, String nom) {
		super(id, prenom, nom);
	}

	public AdresseClient getAdresse() {
		return adresse;
	}

	public void setAdresse(AdresseClient adresse) {
		this.adresse = adresse;
		if(adresse!=null)
		    this.adresse.setClient(this);
	}

}
