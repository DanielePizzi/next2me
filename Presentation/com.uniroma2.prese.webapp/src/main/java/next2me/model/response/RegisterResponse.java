package next2me.model.response;

import next2me.abstracts.AbstractResponse;

public class RegisterResponse extends AbstractResponse {
	
	private String token_sessione;

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
	
	
	
}
