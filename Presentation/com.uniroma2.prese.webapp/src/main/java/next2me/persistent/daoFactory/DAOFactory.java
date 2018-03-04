package next2me.persistent.daoFactory;

import next2me.persistent.dao.PointDAO;
import next2me.persistent.dao.UserDAO;

public abstract class DAOFactory {
	
	public static final String MYSQL = "mysql";

	public abstract UserDAO getUserDAO();
	
	public abstract PointDAO getPointDAO();
	 
	public static DAOFactory getDAOFactory(String tipo) {
	  switch (tipo) {
	    case MYSQL: 
	        return new MySQLDbDAOFactory();
	    default: 
	    	return null;
	    }	
	}
}
