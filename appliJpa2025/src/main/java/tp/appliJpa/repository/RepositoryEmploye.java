package tp.appliJpa.repository;

import tp.appliJpa.entity.Employe;
import tp.appliJpa.repository.generic.RepositoryGeneric;

public interface RepositoryEmploye extends RepositoryGeneric<Employe,Long>{
   //+ autres méthodes de recherches spécifiques aux employés
	
	Employe findEmployeByEmail(String email);
	//à coder via NamedQuery et .getSingleResult() et à tester en TP
}