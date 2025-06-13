package tp.appliJpa.repository2;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tp.appliJpa.entity2.Devise;


@SpringBootTest  
class TestRepositoryDevise {
	
	@Autowired 
	private RepositoryDevise repositoryDevise;
	
	
	@Test
	   public void test_getDeviseByName() {
	     try{
	        System.out.println("test_getDeviseByName");
	        Devise d = repositoryDevise.findDeviseByName("euro");
	        System.out.println("monnaie euro (getDeviseByName) : " + d);
	        assertTrue(d.getMonnaie().equals("euro"));
	        }catch(Exception /*RuntimeException*/ ex){
	      	    System.err.println(ex.getMessage());
	      	    Assertions.fail(ex.getMessage());
	      	}
	   }
		
		@Test
	   public void test_getListeDevises() {
	  
	     try{
	        System.out.println("test_getListeDevises");
	       for(Devise d : repositoryDevise.findAll()){
	    	   System.out.println("\t"+d);
	       }
	        }catch(Exception /*RuntimeException*/ ex){
	      	    System.err.println(ex.getMessage());
	      	    Assertions.fail(ex.getMessage());
	      	}
	   }
}
