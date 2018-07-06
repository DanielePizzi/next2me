package next2me.enums;

public enum ErrorEnum {
	
	UTENTE_ESISTENTE("UTENTE_ESISTENTE"),
	INPUT_NON_VALIDO("INPUT_NON_VALIDO"),
	ERRORE_GENERICO("ERRORE_GENERICO"),
	UTENTE_NON_ESISTENTE("UTENTE NON ESISTENTE"),
	ERRORE_INTERNO("ERRORE INTERNO"),
	PUNTI_INTERESSE_NON_ESISTENTI("NON CI SONO PUNTI DI INTERESSE A TE VICINI"),
	PUNTO_NON_TROVATO("PUNTO NON TROVATO"),
	PUNTO_NON_RIMOSSO("NON E' STATO POSSIBILE RIMUOVERE IL PUNTO SELEZIONATO"),
	PASSWORD_NON_CORRETTA("PASSWORD NON CORRETTA");
	
	private final String err;
	
	ErrorEnum(String val) {
		this.err = val;
	}
	
	public String getDescrizione() {
		return this.err;
	}

}
