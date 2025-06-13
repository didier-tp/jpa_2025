package tp.appliJpa.repository;

import tp.appliJpa.entity.Commande;
import tp.appliJpa.entity.pk.LigneCommandePk;
import tp.appliJpa.repository.generic.RepositoryGeneric;

/*
 DAO : Data Access Object (objet de traitement spécialisé dans l'accès aux données en base)
 comporte des méthodes CRUD (Create/Insert , Rechercher , Update , Delete)
 avec throws RuntimeException implicite
 */

public interface RepositoryCommande extends RepositoryGeneric<Commande,Integer>{
	//+ méthode de recherche spécifiques 
	Commande findByIdWithlines(Integer numCmde);
	
	void deleteLigneCommande(LigneCommandePk pk);
}


