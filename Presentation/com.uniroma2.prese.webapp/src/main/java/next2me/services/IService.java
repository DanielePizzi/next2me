package next2me.services;

import next2me.abstracts.AbstractRequest;
import next2me.abstracts.AbstractResponse;

public interface IService {
	
	public AbstractResponse call(AbstractRequest request, AbstractResponse response);

}
