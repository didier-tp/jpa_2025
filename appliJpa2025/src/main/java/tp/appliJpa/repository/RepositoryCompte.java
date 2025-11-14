package tp.appliJpa.repository;

import java.util.List;

import tp.appliJpa.entity.Compte;
import tp.appliJpa.repository.generic.RepositoryGeneric;

public interface RepositoryCompte extends RepositoryGeneric<Compte,Long>{
   //+ autres méthodes de recherches spécifiques aux comptes
	
	//variante du findById() qui remonte en plus les operations rattachées au compte
     Compte 	findWithOperationsById(long numCompte);
     
   //variante du findById() qui remonte en plus les operations de petites sommes rattachées au compte
     //ne remonte que les operations rattachées au compte que si la valeur absolue du montant est inférieur à maxAmount
     Compte 	findWithSmallOperationsById(long numCompte , double maxAmount);
     
     //retourne tous les comptes d'un client:
     List<Compte> 	findByClientId(long idClient);
     
     List<Compte> findBySoldeMini(double soldeMini);
     
     List<Compte> findAllByType(String typeCompte);
	
}