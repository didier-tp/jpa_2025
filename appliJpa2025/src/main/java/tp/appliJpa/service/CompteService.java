package tp.appliJpa.service;

public interface CompteService {
     void effectuerVirement(double montant,long numCptDeb,long numCptCred) throws RuntimeException;
     //...
}
