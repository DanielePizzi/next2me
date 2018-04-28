package next2me.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import next2me.abstracts.AbstractRequest;
import next2me.abstracts.AbstractResponse;
import next2me.model.response.TestOutput;
import next2me.services.IService;

@Service
public class TestService implements IService {
	
	private static final Logger logger = LoggerFactory.getLogger(TestService.class);

	@Override
	public AbstractResponse call(AbstractRequest request, AbstractResponse response) {
		
		String methodName = "call";
		
		logger.info("Start service [" + methodName + "]. Input: " + request.toString());
		
		TestOutput output = new TestOutput();
		
		output.setO(".........asdasdasdasdasd");

		logger.info("End service [" + methodName + "]. Output: " + response.toString());
		
		return output;
	}

}
