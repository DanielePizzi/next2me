package next2me.model.request;

import java.util.Map;

import javax.validation.constraints.NotNull;

import next2me.abstracts.AbstractRequest;


public class SavePointRequest extends AbstractRequest{
	
	@NotNull
	String username;

	Map pointOfInterest;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Map getPointOfInterest() {
		return pointOfInterest;
	}
	public void setPointOfInterest(Map pointOfInterest) {
		this.pointOfInterest = pointOfInterest;
	}
	@Override
	public String toString() {
		return "SavePointRequest [username=" + username + ", pointOfInterest="
				+ pointOfInterest + "]";
	} 
}
