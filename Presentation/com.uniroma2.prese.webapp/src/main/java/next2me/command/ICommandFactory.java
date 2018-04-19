package next2me.command;

import next2me.abstracts.AbstractRequest;
import next2me.abstracts.AbstractResponse;

public interface ICommandFactory {
	
	/**
	 * 
	 * @param serviceName
	 * @param request
	 * @param response
	 * @return AbstractResponse
	 */
	public AbstractResponse callService(String serviceName, AbstractRequest request, AbstractResponse response);

}
