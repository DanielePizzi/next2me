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
	protected <T extends AbstractRequest, K extends AbstractResponse> ResponseEntity<T> buildResponse(T response, K request) {
		ResponseEntity<T> output = null;
		// SE CI SONO ERRORI
		if (this.hasLoginError(response)) {
			output = new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
		} else if (this.hasTechnicalError(response)) {
			output = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else if (this.hasRunTimeError(response)) {
			output = new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
		} else {
			output = new ResponseEntity<>(response, HttpStatus.OK);
		}
		return output;
	}
	
	private <T> boolean hasLoginError(T response) {
		return false;
	}
	
	private <T> boolean hasTechnicalError(T response) {
		return false;
	}
	
	private <T> boolean hasRunTimeError(T response) {
		return false;
	}

}
