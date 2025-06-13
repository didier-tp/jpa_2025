package tp.appliJpa.repository.generic;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;

@Transactional
public abstract class RepositoryGenericJpa<E,ID> implements RepositoryGeneric<E,ID> {
	
	
	//private EntityManager entityManager; //dans sous classe
	public abstract EntityManager getEntityManager(); 

	private Class<E> entityClass; 

    public RepositoryGenericJpa( Class<E> entityClass) {
    	this.entityClass = entityClass;
    }

	@Override
	public E findById(ID id) {
		return getEntityManager().find(entityClass, id);
        //SELECT .... WHERE id=...
	}

	@Override
	public List<E> findAll() {
		return getEntityManager().createQuery("SELECT e FROM " + 
	            entityClass.getSimpleName() + " e", entityClass).getResultList();
	}

	@Override
	public E insertNew(E e) {
		//en entrée , emp est un nouvel objet employé avec .empId à null (encore inconnu)
		//déclenche automatiquement INSERT INTO Employe(firstname, ....) VALUES(emp.getFirstname() , ....)
		getEntityManager().persist(e);// .empId n'est normalement plus null si auto-incr
		return e; // on retourne l'objet modifié (avec .empId non null)
	}

	@Override
	public E update(E e) {
		getEntityManager().merge(e);
		// déclenche automatiquement UPDATE Employe set .... WHERE idEmp=code
		return e;
	}

	@Override
	public void deleteById(ID id) {
		E eAsupprimer = getEntityManager().find(entityClass, id);
		getEntityManager().remove(eAsupprimer);
		// déclenche automatiquement DELETE FROM E WHERE id=...
	}

}