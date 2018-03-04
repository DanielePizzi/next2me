package next2me.persistent.daoFactory;

import next2me.persistent.dao.PointDAO;
import next2me.persistent.dao.UserDAO;
import next2me.persistent.dao.impl.MySQLPointDAO;
import next2me.persistent.dao.impl.MySQLUserDAO;

public class MySQLDbDAOFactory extends DAOFactory {

	@Override
	public UserDAO getUserDAO() {	
		return new MySQLUserDAO();
	}

	@Override
	public PointDAO getPointDAO() {
		return new MySQLPointDAO();
	}
	

}
