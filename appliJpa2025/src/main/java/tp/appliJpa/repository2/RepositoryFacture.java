package tp.appliJpa.repository2;

import java.util.Map;

import tp.appliJpa.entity2.Article;
import tp.appliJpa.entity2.Facture;
import tp.appliJpa.repository.generic.RepositoryGeneric;

public interface RepositoryFacture extends RepositoryGeneric<Facture,Long>{
	
	Facture findByIdWithLines(Long numFacture);
	
	

}