package tp.myJpa.dao;

import java.util.List;

import tp.myJpa.dao.generic.DaoGeneric;
import tp.myJpa.entity.Employe;

public interface DaoEmploye extends DaoGeneric<Employe,Long>{
	

	//on hérite automatiquement de findAll() , de insertNew , ....

}
