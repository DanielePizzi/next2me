package next2me.command.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import next2me.abstracts.AbstractRequest;
import next2me.abstracts.AbstractResponse;
import next2me.bean.ServiceCenter;
import next2me.command.ICommandFactory;
import next2me.enums.ErrorEnum;
import next2me.services.AbstractService;
import next2me.services.impl.RegistrazioneService;
import next2me.utils.ErrorHandler;

@Component
public class CommandFactory implements ICommandFactory {
	
	private static final Logger logger = LoggerFactory.getLogger(CommandFactory.class);
	
	@Autowired
	private ServiceCenter serviceCenter;

	@Override
	public AbstractResponse callService(String serviceName, AbstractRequest request, AbstractResponse response) {
		
		AbstractResponse output = response;
		
		if (!serviceCenter.getServices().containsKey(serviceName)) {
			// GESTIONE ERRORE SERVICE NOT FOUND
		} else {
			AbstractService service = (AbstractService) serviceCenter.getServices().get(serviceName);
			try {
				output = (AbstractResponse) service.execute(request, response);
			} catch (Exception e) {
				logger.error("Runtime Exception Catched: ", e);
				output = ErrorHandler.addError(response, request, ErrorEnum.ERRORE_GENERICO);
			}
		}
		
		return output;
	}

}
