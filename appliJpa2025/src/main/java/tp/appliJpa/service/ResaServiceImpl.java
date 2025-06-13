package tp.appliJpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tp.appliJpa.entity.ResaAvecVersion;
import tp.appliJpa.repository.RepositoryResaAvecVersion;

@Transactional
@Service
public class ResaServiceImpl implements ResaService {
	
	@Autowired
	RepositoryResaAvecVersion repositoryResaAvecVersion;

	@Override
	public boolean essayerReservation(long id_resa, long numClient, long nbMsPause) {
		boolean ok= false;
		try {
			ResaAvecVersion resa = repositoryResaAvecVersion.findById(id_resa);
			resa.setNumClient(numClient);
			Thread.sleep(nbMsPause); //pause de quelques ms (simulation traitement long ...)
			repositoryResaAvecVersion.update(resa);
			ok=true;
		} catch (Exception e) {
			//e.printStackTrace();
			ok=false;
			System.err.println(e.getMessage());
		}finally {
		    System.out.println("essayerReservation numClient="+numClient + " nbMsPause="+nbMsPause + " ok="+ok );
		}
		return ok;
		
		//CAS D'ECOLE (simulation multi-thread à fiabiliser/peaufiner) !!!!!
	}

}
