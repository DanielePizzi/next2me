package next2me.command.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import next2me.abstracts.AbstractRequest;
import next2me.abstracts.AbstractResponse;
import next2me.bean.ServiceCenter;
import next2me.command.ICommandFactory;
import next2me.services.IService;

@Component
public class CommandFactory implements ICommandFactory {
	
	@Autowired
	@Qualifier("serviceCenter")
	private ServiceCenter serviceCenter;

	@Override
	public AbstractResponse callService(String serviceName, AbstractRequest request, AbstractResponse response) {
		
		AbstractResponse output = response;
		
		if (!serviceCenter.getServices().containsKey(serviceName)) {
			// GESTIONE ERRORE SERVICE NOT FOUND
		} else {
			IService service = (IService) serviceCenter.getServices().get(serviceName);
			output = (AbstractResponse) service.call(request, response);
		}
		
		return output;
	}

}
