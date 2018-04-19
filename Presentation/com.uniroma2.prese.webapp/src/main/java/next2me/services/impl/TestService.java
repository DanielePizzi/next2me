package next2me.services.impl;

import org.springframework.stereotype.Service;

import next2me.abstracts.AbstractRequest;
import next2me.abstracts.AbstractResponse;
import next2me.services.IService;

@Service
public class TestService implements IService {

	@Override
	public AbstractResponse call(AbstractRequest request, AbstractResponse response) {
		// SERVICE LOGIC
		return null;
	}

}
