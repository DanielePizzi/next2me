package next2me.enums;

public enum ErrorEnum {
	
	UTENTE_ESISTENTE("UTENTE_ESISTENTE"),
	INPUT_NON_VALIDO("INPUT_NON_VALIDO"),
	ERRORE_GENERICO("ERRORE_GENERICO");
	
	private final String err;
	
	ErrorEnum(String val) {
		this.err = val;
	}
	
	public String getDescrizione() {
		return this.err;
	}

}
