package tp.appliJpa.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;



/*
 NB: le mot clef FETCH permet de remonter sur demande les operations reliées au compte
 en un seul gros select (même comportement que EAGER mais sur demande seulement).
 pas besoin de ON operation.num_compte = compte.numero car ces infos
 sont déjà précisées via @Id et @JoinColumn dans les classes JAVA
 et de toute façon requête JPQL exprimée qu'avec des noms java et jamais avec noms de table 
 ou de colonne.
 */

@Entity
/*
@NamedEntityGraph(name = "entity-graph-compte-operations",
attributeNodes = {
	    @NamedAttributeNode("operations")
	  }
)
*/
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_compte", 
 discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("CompteCourant")
@NamedQuery(name="Compte.findWithOperationsById",
            query="SELECT c FROM Compte c LEFT JOIN FETCH c.operations WHERE c.numero = :numCompte")
@NamedQuery(name="Compte.findByClientId",
    query="SELECT cpt FROM Client cli LEFT JOIN  cli.comptes cpt WHERE cli.id = :idClient")
@NamedQuery(name="Compte.findBySoldeMini",
     query="SELECT cpt FROM Compte cpt WHERE cpt.solde >= :soldeMini")

@NamedEntityGraph(name = "entity-graph-compte-operations",
	attributeNodes = {
	@NamedAttributeNode(value="operations"),
	}
)

//Dans la requête JPQL ci dessus cpt est un alias pour une instance appartenant
//à la collection cli.comptes
//@Table(name="Compte")
public class Compte {
	
	@Column(name="type_compte", insertable = false , updatable = false)
	private String typeCompte; //+get/set
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long  numero;
	
	@Column(name="label")
	private String label;
	
	private Double solde;
	
	//NB: mappedBy="nom_java_relation_inverse"
	//du coté secondaire d'une relation bidirectionnelle
	@OneToMany(mappedBy="compte" , fetch = FetchType.LAZY)
	private List<Operation> operations = new ArrayList<>() ;  //+get/set
	
	@ManyToMany(mappedBy="comptes" , fetch = FetchType.LAZY)
	private List<Client> clients = new ArrayList<>() ;  //+get/set



@Override
public String toString() {
	return "Compte [numero=" + numero + ", label=" + label + ", solde=" + solde + "]";
}



public Compte() {
	super();
}



public Compte(Long numero, String label, Double solde) {
	super();
	this.numero = numero;
	this.label = label;
	this.solde = solde;
}



@Override
public int hashCode() {
	return Objects.hash(label, numero, solde);
}



@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Compte other = (Compte) obj;
	return Objects.equals(label, other.label) && Objects.equals(numero, other.numero)
			&& Objects.equals(solde, other.solde);
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



public String getTypeCompte() {
	return typeCompte;
}



public void setTypeCompte(String typeCompte) {
	this.typeCompte = typeCompte;
}



}
