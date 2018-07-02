package next2me.utils;

import java.util.ArrayList;
import java.util.List;

import next2me.abstracts.AbstractRequest;
import next2me.abstracts.AbstractResponse;
import next2me.enums.ErrorEnum;

public final class ErrorHandler {
	
	public static <T extends AbstractResponse, K extends AbstractRequest> T addError(T resp, K req, ErrorEnum error) {
		T out = (T) resp;
		List<String> errList = getErrorList(out);
		out.setErrorList(errList);
		out.getErrorList().add(error.getDescrizione());
		return out;
	}
	
	private static <T extends AbstractResponse> List<String> getErrorList(T out) {
		if (out.getErrorList() == null || out.getErrorList().isEmpty()) {
			return new ArrayList<String>();
		}
		return out.getErrorList();
	}

}
