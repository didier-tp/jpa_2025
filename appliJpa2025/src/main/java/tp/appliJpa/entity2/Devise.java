package tp.appliJpa.entity2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="devise") //here as annotation or specified/overrided in orm.xml
public  class Devise  {

	@Column(name="d_change") //here as annotation or specified/overrided in orm.xml
	private Double change;
	
	@Column(length=64)
	private String monnaie;
	
	@Id
	@Column(length=8 ,name="code_devise")
	private String codeDevise;

	public Devise(){
		super(); 
	}      
	public String toString(){
		return "Devise("+ "change=" + change+","+ "monnaie=" + monnaie+","+ "codeDevise=" + codeDevise + ")";
	}

	public java.io.Serializable getPk(){
	 		return codeDevise;
 	}
	public Double getChange() {
		return this.change;
	}
	public void setChange(Double change){
		this.change=change;
	}
	public String getMonnaie() {
		return this.monnaie;
	}
	public void setMonnaie(String monnaie){
		this.monnaie=monnaie;
	}
	public String getCodeDevise() {
		return this.codeDevise;
	}
	public void setCodeDevise(String codeDevise){
		this.codeDevise=codeDevise;
	}

       
} 