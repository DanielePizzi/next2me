package next2me.abstracts;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractResponse {
	
	private List<String> errorList;

	/**
	 * @return the errorList
	 */
	public List<String> getErrorList() {
		return this.errorList;
	}

	/**
	 * @param errorList the errorList to set
	 */
	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}

}
