package next2me.model.request;

import javax.validation.constraints.NotNull;

import next2me.abstracts.AbstractRequest;

public class LoginRequest extends AbstractRequest{
	
	@NotNull
	private String email;
	@NotNull
	private String password;
	private String name;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	@Override
	public String toString() {
		return "LoginRequest [email=" + email + ", password=" + password + "]";
	}
	
	
}
