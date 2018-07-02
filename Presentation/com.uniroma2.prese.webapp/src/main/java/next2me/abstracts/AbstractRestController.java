package next2me.abstracts;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class AbstractRestController {
	
	/**
	 * 
	 * @param response
	 * @param request
	 * @return ResponseEntity
	 */
	protected <T extends AbstractRequest, K extends AbstractResponse> ResponseEntity<K> buildResponse(T request, K response) {
		ResponseEntity<K> output = null;
		// SE CI SONO ERRORI
		if (this.hasErrors(response)) {
			output = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			output = new ResponseEntity<>(response, HttpStatus.OK);
		}
		return output;
	}
	
	private <K extends AbstractResponse> boolean hasErrors(K response) {
		if (response.getErrorList() != null && !response.getErrorList().isEmpty()) {
			return true;
		}
		return false;
	}
	
//	private <K extends AbstractResponse> boolean hasTechnicalError(K response) {
//		return false;
//	}
//	
//	private <K extends AbstractResponse> boolean hasRunTimeError(K response) {
//		return false;
//	}

}
