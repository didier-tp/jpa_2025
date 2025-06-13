package tp.appliJpa.repository;

import tp.appliJpa.dto.ClientEssential;
import tp.appliJpa.entity.Client;
import tp.appliJpa.repository.generic.RepositoryGeneric;

public interface RepositoryClient extends RepositoryGeneric<Client,Long>{
   //+ autres méthodes de recherches spécifiques aux clients
	
   Client clientAvecComptes(Long idClient); //2 niveaux (via entityGraph)
   Client clientAvecComptesEtOperations(Long idClient); //3 niveaux (via entityGraph et subGraph)
   
   //ici ou sur autre interface complémentaire:
   ClientEssential getClientEssentialFromClientId(Long idClient); //exemple de projection JPA
	
}