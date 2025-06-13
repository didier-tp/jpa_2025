package tp.appliJpa.dto;

public class ClientEssential {
	
	private String firstname;
	private String lastname;
	private Integer accountNumber; //nombre de comptes bancaires que le client possède (éventuellement à null)
	
	
	public ClientEssential() {
		this(null,null,null);
	}
	
	public ClientEssential(String firstname, String lastname) {
		this(firstname,lastname,null);
	}


	public ClientEssential(String firstname, String lastname, Integer accountNumber) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.accountNumber = accountNumber;
	}


	@Override
	public String toString() {
		return "ClientEssential [firstname=" + firstname + ", lastname=" + lastname + ", accountNumber=" + accountNumber
				+ "]";
	}
	
	

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Integer getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	

}
