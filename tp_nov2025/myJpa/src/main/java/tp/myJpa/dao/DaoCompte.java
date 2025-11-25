package tp.myJpa.dao;

import tp.myJpa.dao.generic.DaoGeneric;
import tp.myJpa.entity.Compte;

public interface DaoCompte extends DaoGeneric<Compte,Long>{
   //+ eventuels .find specifiques
   public Compte findWithOperationsById(long numCompte);
}
