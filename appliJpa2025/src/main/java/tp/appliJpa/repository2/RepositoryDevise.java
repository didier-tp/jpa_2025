package tp.appliJpa.repository2;

import tp.appliJpa.entity2.Devise;
import tp.appliJpa.repository.generic.RepositoryGeneric;

public interface RepositoryDevise extends RepositoryGeneric<Devise,String>{
   //+ autres méthodes de recherches spécifiques aux devises
	
	Devise findDeviseByName(String name);
	//à coder via NamedQuery et .getSingleResult() et à tester en TP
}