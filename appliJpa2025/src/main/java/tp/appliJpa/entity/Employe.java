package tp.appliJpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

@Entity
//@Table(name="employe")
@NamedQuery(name = "Employe.findEmployeByEmail" ,
            query = "SELECT e FROM Employe e WHERE e.email = :email")
public class Employe {
	
	@Id //identifiant (clef primaire / pk)
	@GeneratedValue(strategy = GenerationType.IDENTITY)//auto_incr qui remonte en mémoire 
	@Column(name="emp_id" )
	private Long id;
	
	@Column(name="firstname" , length = 64)
	private String firstName;
	
	@Column(name="lastname" , length= 64)
	private String lastName;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	private String email;
	
	//@Transient
	private String login;
	
	private String password;
	
	

	@Override
	public String toString() {
		return "Employe [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber="
				+ phoneNumber + ", email=" + email + ", login=" + login + ", password=" + password + "]";
	}


	public Employe() {
		super();
	}


	public Employe(Long id, String firstName, String lastName, String phoneNumber, String email, String login,
			String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.login = login;
		this.password = password;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

   
	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}
  

	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	

}
