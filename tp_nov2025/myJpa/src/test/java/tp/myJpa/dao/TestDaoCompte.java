package tp.myJpa.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tp.myJpa.entity.Compte;
import tp.myJpa.entity.Operation;

@SpringBootTest
public class TestDaoCompte {
	
	@Autowired
	private DaoCompte daoCompte;
	
	@Autowired
	private DaoOperation daoOperation;
	

	@Test
	void testCompteAvecOperations() {
		Compte c1 = new Compte(null,"compteA",50.0);
		daoCompte.insertNew(c1);
		System.out.println("c1.getNumero()=" + c1.getNumero());
		
		Operation op1C1 = new Operation(null,"achat xxx",-5.6);
		op1C1.setCompte(c1);
		daoOperation.insertNew(op1C1);
		Operation op2C1 = new Operation(null,"achat yyy",-45.6);
		op2C1.setCompte(c1);
		daoOperation.insertNew(op2C1);
		//relire et afficher le compte 1
		//afficher les operations associées au compte 1
		Compte c1Relu = daoCompte.findById(c1.getNumero());
		System.out.println("c1Relu="+c1Relu);
		for(Operation op : c1Relu.getOperations()){
			System.out.println("\t op="+op);
			}
	}

}
