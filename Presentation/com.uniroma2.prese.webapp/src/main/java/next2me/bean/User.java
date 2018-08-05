package next2me.bean;

public class User {

	private String token_sessione;
	private String name;
	private String password;
	private String email;
	/**
	 * @return the token_sessione
	 */
	public String getToken_sessione() {
		return token_sessione;
	}
	/**
	 * @param token_sessione the token_sessione to set
	 */
	public void setToken_sessione(String token_sessione) {
		this.token_sessione = token_sessione;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [token_sessione=" + token_sessione + ", name=" + name + ", password=" + password + ", email="
				+ email + "]";
	}
	
}
