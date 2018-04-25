package next2me.services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import next2me.abstracts.AbstractRequest;
import next2me.abstracts.AbstractResponse;

@Service
public interface IService {
	
	public AbstractResponse call(AbstractRequest request, AbstractResponse response);

}
