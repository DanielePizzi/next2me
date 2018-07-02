package next2me.services;

import next2me.abstracts.AbstractRequest;
import next2me.abstracts.AbstractResponse;
import next2me.persistent.daoFactory.DAOFactory;

public abstract class AbstractService {
	
	public abstract AbstractResponse execute(AbstractRequest request, AbstractResponse response);

	public static DAOFactory getDao() {
		return DAOFactory.getDAOFactory(DAOFactory.MYSQL);
	}
}
