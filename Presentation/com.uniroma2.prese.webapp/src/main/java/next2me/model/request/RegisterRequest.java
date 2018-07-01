package next2me.model.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import next2me.abstracts.AbstractRequest;


public class RegisterRequest extends AbstractRequest{
	
	private String name;
	private String password;
	private String email;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "RegisterRequest [name=" + name + ", password=" + password
				+ ", email=" + email + "]";
	}
	
}
