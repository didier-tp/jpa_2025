package tp.appliJpa.repository;

import java.util.List;

import tp.appliJpa.entity.Operation;
import tp.appliJpa.repository.generic.RepositoryGeneric;

public interface RepositoryOperation extends RepositoryGeneric<Operation,Long>{
   //+ autres méthodes de recherches spécifiques aux operations
	
	List<Operation> findOperationsByNumCompte(long numCompte);
	
}