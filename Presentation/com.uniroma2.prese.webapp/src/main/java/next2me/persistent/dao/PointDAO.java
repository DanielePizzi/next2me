package next2me.persistent.dao;

import java.util.ArrayList;

import next2me.model.utils.PointDistance;
import next2me.persistent.entity.Point;

public interface PointDAO {
	public void addPoint(Point point);
	public ArrayList<PointDistance> getPointNear(int id, String categoria, double latitudine, double longitudine);
	public boolean removePointDAO(Point point);
	public Point getPoinById(int idPoint);
}
