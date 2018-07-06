package next2me.model.request;

import next2me.abstracts.AbstractRequest;

public class RemovePointRequest extends AbstractRequest{
	
	int idPoint;

	public int getIdPoint() {
		return idPoint;
	}

	public void setIdPoint(int idPoint) {
		this.idPoint = idPoint;
	}

	@Override
	public String toString() {
		return "RemovePointRequest [idPoint=" + idPoint + ", getIdPoint()="
				+ getIdPoint() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
