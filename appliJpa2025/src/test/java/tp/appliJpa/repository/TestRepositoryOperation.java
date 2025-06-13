package tp.appliJpa.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tp.appliJpa.entity.Operation;

@SpringBootTest   //à lancer avec Run as ... / JUnit Test
class TestRepositoryOperation {
	
	@Autowired //equivalent de @Inject	
	private RepositoryOperation repositoryOperation;

	@Test
	void testOperationsDeCompte() {
		
		List<Operation> operations = repositoryOperation.findOperationsByNumCompte(1);
		for (Operation op : operations) {
			System.out.println(op);
		}
		Assertions.assertTrue(operations.size()>=1);
		
		
	}

}
