package next2me.model.response;

import next2me.abstracts.AbstractResponse;

public class LoginResponse extends AbstractResponse {
	
	private String token_sessione;
	private String nome;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getToken_sessione() {
		return token_sessione;
	}
	public void setToken_sessione(String token_sessione) {
		this.token_sessione = token_sessione;
	}
	
}
