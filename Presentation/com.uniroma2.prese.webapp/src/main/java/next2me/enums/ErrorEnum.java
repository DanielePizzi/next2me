package next2me.enums;

public enum ErrorEnum {
	
	UTENTE_ESISTENTE("UTENTE_ESISTENTE"),
	INPUT_NON_VALIDO("INPUT_NON_VALIDO"),
	ERRORE_GENERICO("ERRORE_GENERICO"),
	UTENTE_NON_ESISTENTE("UTENTE NON ESISTENTE"),
	PASSWORD_NON_CORRETTA("PASSWORD NON CORRETTA");
	
	private final String err;
	
	ErrorEnum(String val) {
		this.err = val;
	}
	
	public String getDescrizione() {
		return this.err;
	}

}
