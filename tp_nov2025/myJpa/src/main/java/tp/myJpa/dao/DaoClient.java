package tp.myJpa.dao;

import tp.myJpa.dao.generic.DaoGeneric;
import tp.myJpa.entity.Client;

public interface DaoClient extends DaoGeneric<Client,Long>{
   //+ eventuels .find specifiques
	Client findByIdWithComptes(Long idClient);
}
