package next2me.command;

import org.springframework.stereotype.Component;

import next2me.abstracts.AbstractRequest;
import next2me.abstracts.AbstractResponse;

@Component
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
