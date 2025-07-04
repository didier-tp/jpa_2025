package tp.appliJpa.repository.generic;

import java.util.List;

//E = type de l'entité persitante , ID=type de clef primaire (ex: Long)
public interface RepositoryGeneric<E,ID> {

	E findById(ID id);

	List<E> findAll();

	E insertNew(E e);

	E update(E e);

	void deleteById(ID id);
}