package tp.appliJpa.repository;

import tp.appliJpa.entity.BasicClient;
import tp.appliJpa.entity.ClientAvecAdresse;
import tp.appliJpa.repository.generic.RepositoryGeneric;

public interface RepositoryClientAvecAdresse extends RepositoryGeneric<ClientAvecAdresse,Long>{
   //+ autres méthodes de recherches spécifiques aux clients
	public BasicClient getBasicClientWithoutAddress(long id);
     
	
}