package next2me.model.response;

import java.util.List;
import java.util.Map;

import next2me.abstracts.AbstractResponse;

public class GetPointResponse extends AbstractResponse {
	
	
	List<Map> pointOfInterest;
	
	String distanza;

	public List<Map> getPointOfInterest() {
		return pointOfInterest;
	}

	public void setPointOfInterest(List<Map> pointOfInterest) {
		this.pointOfInterest = pointOfInterest;
	}
	
	public String getDistanza() {
		return distanza;
	}

	public void setDistanza(String distanza) {
		this.distanza = distanza;
	}

	@Override
	public String toString() {
		return "GetPointResponse [pointOfInterest=" + pointOfInterest + ", distanza=" + distanza + "]";
	}

}
