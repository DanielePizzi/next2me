package next2me.persistent.dao.impl;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.transaction.annotation.Transactional;

import next2me.model.utils.PointDistance;
import next2me.persistent.dao.PointDAO;
import next2me.persistent.entity.Point;
import next2me.utils.CalcolaPointNear;



public class MySQLPointDAO extends SessionFactoryHibernate implements PointDAO  {
	
	private static final String CLASS = "MySQLPointDAO";
	
	Logger logger = Logger.getLogger("FINDMENEAR");
	

	@Transactional
	public void addPoint(Point point) {
		 getSession().save(point);
	     getSession().flush(); 
	}

	@Override
	@Transactional
	public ArrayList<PointDistance> getPointNear(int idUser ,String categoria, double latitudine, double longitudine) {
		@SuppressWarnings("unchecked")
		ArrayList<Point> listResult = ((ArrayList<Point>) getSession().createQuery("FROM Point WHERE Tipo = :categoria AND idUser = :idUser")
				.setParameter("categoria", categoria).setParameter("idUser", idUser).list());
		if(listResult == null || listResult.isEmpty()) return null;
		return CalcolaPointNear.distanceMin(listResult, latitudine, longitudine);
	}

	@Override
	@Transactional
	public boolean removePointDAO(Point point) {
		String method = "removePointDAO";
		logger.debug(String.format("%s-%s:: inpu%st", CLASS,method,point.toString()));
		try{
			Query query = getSession().createQuery("DELETE FROM Point WHERE id = "+ point.getId());
			int deleted = query.executeUpdate();
			logger.debug(String.format("%s-%s puntiCancellati[%s]",CLASS,method,deleted));
			if(deleted == 0) return false;
		}catch(Exception e){
			logger.debug(String.format("%s-%s error[%s]",CLASS,method,e));
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public Point getPoinById(int id) {
		String method = "getPoinById";
		Point point = null;
		try{
			point = (Point) getSession().createQuery("FROM Point WHERE id = :id")
					.setParameter("id", id).uniqueResult();			
		}catch(Exception e){
			logger.debug(String.format("%s-%s error[%s]",CLASS,method,e));
			return null;
		}
		return point;
	}
	
}
