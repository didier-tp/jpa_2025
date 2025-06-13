package tp.appliJpa.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tp.appliJpa.entity.ResaAvecVersion;
import tp.appliJpa.repository.RepositoryResaAvecVersion;

@SpringBootTest   //à lancer avec Run as ... / JUnit Test
class TestResaAvecVersion {
	
	@Autowired //equivalent de @Inject	
	private RepositoryResaAvecVersion repositoryResaAvecVersion;
	
	
	@Autowired //equivalent de @Inject	
	private ResaService resaService;
	
	//CAS D'ECOLE (simulation multi-thread à fiabiliser/peaufiner) !!!!!
	
	public void executeResaByOtherThread(long idResa,long numClient,long nbMsPause) {
		Thread t = new Thread(()-> {
			boolean resaOkClient = resaService.essayerReservation(idResa, numClient,nbMsPause);
			System.out.println("resaOkClient" + numClient+ "=" + resaOkClient);
			ResaAvecVersion resaConvoiteeRelue = repositoryResaAvecVersion.findById(idResa);
			System.out.println("resaConvoiteeRelue apres reservation par client " + numClient+ "="+resaConvoiteeRelue);
		});
		t.start();
	}
	public void pauseThreadCourant(long nbMs) {
		try {
			Thread.sleep(nbMs);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testResaAvecVersionV1() {
		ResaAvecVersion resaConvoitee = repositoryResaAvecVersion.insertNew(
				new ResaAvecVersion(null,"choseConvoitee" , null));
		Long idResa = resaConvoitee.getIdResa();
		ResaAvecVersion resaConvoiteeRelue = repositoryResaAvecVersion.findById(idResa);
		System.out.println("resaConvoiteeRelue initiale="+resaConvoiteeRelue);

		executeResaByOtherThread(idResa, 1,400);
		pauseThreadCourant(450);
		executeResaByOtherThread(idResa, 2,400);//resa l'une apres l'autre sans concurrence/conflit
		pauseThreadCourant(450);//pour avoir le temps de voir les derniers affichages
	}
	
	@Test
	void testResaAvecVersionV2() {
		ResaAvecVersion resaConvoitee = repositoryResaAvecVersion.insertNew(
				new ResaAvecVersion(null,"choseConvoitee" , null));
		Long idResa = resaConvoitee.getIdResa();
		ResaAvecVersion resaConvoiteeRelue = repositoryResaAvecVersion.findById(idResa);
		System.out.println("resaConvoiteeRelue initiale="+resaConvoiteeRelue);

		executeResaByOtherThread(idResa, 1,1000);//1000ms = pas rapide
		executeResaByOtherThread(idResa, 2,500);//500ms = un peu plus rapide
		pauseThreadCourant(1200); //1200ms de pause pour attentre fin des 2 threads
		
	}

	
}
