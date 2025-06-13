package tp.appliJpa.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tp.appliJpa.dto.ClientEssential;

@SpringBootTest  
class TestRepositoryClientAvecProjectionJpa {
	
	@Autowired 	
	private RepositoryClient repositoryClient;

	@Test
	void testProjectionSurClientEssential() {
		
		ClientEssential  clientEssential = repositoryClient.getClientEssentialFromClientId(2L);
		assertNotNull(clientEssential);
		System.out.println("clientEssential="+clientEssential);
		
		//NB: sur application complète , une projection JPA permet de directement récupérer un résultat de requête sous forme
		//d'objet de la couche métier (voir DTO)
		//cela permet d'éviter de devoir effectuer d'ultérieurs conversions de type entity --> DTO avec MapStruct ou autres
		//et cela permet d'obtenir quelquefois de meilleurs performances et de limiter les new/destructions d'instances par la JVM
	}
	
}
