package tp.myJpa.dao;

import java.util.List;

import tp.myJpa.entity.Employe;

public interface DaoEmploye {
	
	List<Employe> findAll();
	Employe insertNew(Employe e); //en retour : Employe avec numero auto incrémenté
	//...

}
