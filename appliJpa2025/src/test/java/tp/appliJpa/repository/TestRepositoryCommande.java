package tp.appliJpa.repository;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tp.appliJpa.entity.Commande;
import tp.appliJpa.entity.LigneCommande;

@SpringBootTest
class TestRepositoryCommande {
	
	@Autowired
	private RepositoryCommande daoCommande;
	
	
	@Test
	void testCommande_LigneCommande() {
		Commande c1 = daoCommande.insertNew(new Commande(null,new Date()));//first save to have a auto_incr command number
		c1.addLigneCommande(new LigneCommande("refProduit1",1,12.5));
		c1.addLigneCommande(new LigneCommande("refProduit2",3,2.5));
		c1.addLigneCommande(new LigneCommande("refProduit3",1,6.5));
		daoCommande.update(c1);//with internal merge cascade 
		
		Commande c1ReloadWithLines = daoCommande.findByIdWithlines(c1.getNumero());
		System.out.println("c1ReloadWithLines="+c1ReloadWithLines);
		//V1(List): for(LigneCommande l : c1ReloadWithLines.getLignes()) {
		//V2(Map):c1.getLignes().remove(1);
		for(LigneCommande l : c1ReloadWithLines.getLignes().values()) {
			System.out.println("\t"+l);
		}
		
		//suppression ligne 1 de c1:
		LigneCommande ligneNumero1DeC1 = c1.getLignes().get(1);//ici map.get(numLigne) avec numLine = 1,2,... (différent de index=0,1,n)
		c1.removeLigneCommande(ligneNumero1DeC1); // et les autres lignes ne changent pas de numero
		daoCommande.deleteLigneCommande(ligneNumero1DeC1.getPk());
		
		//modification ligne 2 de c1:
		LigneCommande ligneNumero2DeC1 = c1.getLignes().get(2);//ici map.get(numLigne) avec numLine = 1,2,... (différent de index=0,1,n)
		ligneNumero2DeC1.setQuantite(5); ligneNumero2DeC1.setPrixUnitaire(2.9);
		daoCommande.update(c1);//with internal merge cascade
		
		
		c1ReloadWithLines = daoCommande.findByIdWithlines(c1.getNumero());
		System.out.println("c1ReloadWithLines after update="+c1ReloadWithLines);
		//V1(List): for(LigneCommande l : c1ReloadWithLines.getLignes()) {
		//V2(Map):
		for(LigneCommande l : c1ReloadWithLines.getLignes().values()) {
			System.out.println("\t"+l);
		}
		
		Commande c2 = daoCommande.insertNew(new Commande(null,new Date()));//first save to have a auto_incr command number
		c2.addLigneCommande(new LigneCommande("refProduitA",1,112.5));
		c2.addLigneCommande(new LigneCommande("refProduitB",2,28.5));
		daoCommande.update(c2);//with internal merge cascade 
		
		
		Commande c2ReloadWithLines = daoCommande.findByIdWithlines(c2.getNumero());
		System.out.println("c2ReloadWithLines="+c2ReloadWithLines);
		//V1(List): for(LigneCommande l : c1ReloadWithLines.getLignes()) {
		//V2(Map):
		for(LigneCommande l : c2ReloadWithLines.getLignes().values()) {
			System.out.println("\t"+l);
		}
		
		//daoCommande.deleteById(c2.getNumero());//with internal delete cascade 
		
	}
	
	
	

}
