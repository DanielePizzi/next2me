package next2me.model.request;

import next2me.abstracts.AbstractRequest;

public class TestInput extends AbstractRequest {
	
	private String a;

	/**
	 * @return the a
	 */
	public String getA() {
		return a;
	}

	/**
	 * @param a the a to set
	 */
	public void setA(String a) {
		this.a = a;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TestInput [a=" + a + "]";
	}
	
}
