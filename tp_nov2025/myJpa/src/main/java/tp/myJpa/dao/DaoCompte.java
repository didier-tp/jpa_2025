package tp.myJpa.dao;

import java.util.List;

import tp.myJpa.dao.generic.DaoGeneric;
import tp.myJpa.entity.Compte;

public interface DaoCompte extends DaoGeneric<Compte,Long>{
   //+ eventuels .find specifiques
   public Compte findWithOperationsById(long numCompte);
   List<Compte> findByClientId(long idClient);
   List<Compte> findComptesByNumCli(long idClient);
}
