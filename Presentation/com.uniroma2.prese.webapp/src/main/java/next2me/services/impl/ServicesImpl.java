package next2me.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import next2me.model.response.GetPointResponse;
import next2me.model.response.RemovePointResponse;
import next2me.model.utils.PointDistance;
import next2me.persistent.dao.PointDAO;
import next2me.persistent.dao.UserDAO;
import next2me.persistent.daoFactory.DAOFactory;
import next2me.persistent.entity.Point;
import next2me.persistent.entity.User;
import next2me.services.IServices;


public class ServicesImpl implements IServices{
	
	private static final String CLASS = "ServicesImpl";
	
	Logger logger = Logger.getLogger("FINDMENEAR");
	
	DAOFactory mysqlDAOfactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
	
	@Override
	public void savePoint(String username, String nome, String citta, String stato, String lat, String lng, String tipo, String descrizione) {
		String method = "savePoint";
		
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::           START",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::  - parametri in INPUT - inizio elenco",CLASS,method));
		logger.debug(String.format("%s - %s::username[%s]",CLASS,method,username));
		logger.debug(String.format("%s - %s::nome[%s]",CLASS,method,nome));
		logger.debug(String.format("%s - %s::citta[%s]",CLASS,method,citta));
		logger.debug(String.format("%s - %s::stato[%s]",CLASS,method,stato));
		logger.debug(String.format("%s - %s::lat[%s]",CLASS,method,lat));
		logger.debug(String.format("%s - %s::lng[%s]",CLASS,method,lng));
		logger.debug(String.format("%s - %s::tipo[%s]",CLASS,method,tipo));
		logger.debug(String.format("%s - %s::descrizione[%s]",CLASS,method,descrizione));
		logger.debug(String.format("%s - %s::  - parametri in INPUT - fine elenco",CLASS,method));
		PointDAO pointDAO = mysqlDAOfactory.getPointDAO();
		
		UserDAO userDAO = mysqlDAOfactory.getUserDAO();
		
		User user = userDAO.getUserName(username);
		
		if(user!=null){
			logger.debug(String.format("%s - %s::inserimento punto di interesse nel db per l'utente[%s]",CLASS,method,user.toString()));
		}else{
			logger.debug(String.format("%s - %s::inserimento punto di interesse nel db per l'utente[null]",CLASS,method));
		}
		Point point = new Point();
		
		point.setNome(nome);
		point.setCitta(citta);
		point.setLat(lat);
		point.setLng(lng);
		point.setStato(stato);
		point.setTipo(tipo);
		point.setDescrizione(descrizione);
		point.setUser(user);
		
		
		
		try{
			logger.debug(String.format("%s - %s::inserimento punto di interesse nel db",CLASS,method));
			pointDAO.addPoint(point);			
		}catch(Exception e){
			logger.debug(String.format("%s - %s::ERROR[%s]",CLASS,method,e));
		}
		logger.debug(String.format("%s - %s::punto di interesse registrato correttamente",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::           END",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		
	}


	@Override
	public GetPointResponse getPoint(String username, String categoria, double latitudine, double longitudine) {
		String method = "getPoint";
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::           START",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		
		GetPointResponse pointResponse = new GetPointResponse();
		PointDAO pointDAO = mysqlDAOfactory.getPointDAO();
		UserDAO userDAO = mysqlDAOfactory.getUserDAO();
		HashMap<String,Object> pointLocation = new HashMap<String, Object>();
		HashMap<String,Object> geometry = new HashMap<String, Object>();
		HashMap<String,Object> location = new HashMap<String, Object>();
		ArrayList<Map> results = new ArrayList<Map>();
		User user = userDAO.getUserName(username);
		
		if(user == null){
			logger.debug(String.format("%s-%s:: user null",CLASS,method));
			pointResponse.setEsito(false);
			pointResponse.setDescrizione("UTENTE NON ESISTENTE");
			return pointResponse;
		}
		
		PointDistance pointDistance = pointDAO.getPointNear(user.getId(),categoria, latitudine, longitudine);
		
		if(pointDistance == null){
			pointResponse.setEsito(false);
			pointResponse.setDescrizione("NON CI SONO PUNTI DI INTERESSE A TE VICINI");
			logger.debug(String.format("%s - %s::point null",CLASS,method));
			return pointResponse;
		}
		
		Point point = pointDistance.getPoint();
		
		logger.debug(String.format("%s-%s:: punto da restituire[%s]",CLASS,method,point.toString()));
		
		location.put("lat", Double.parseDouble(point.getLat()));
		location.put("lng", Double.parseDouble(point.getLng()));
		location.put("distanza", pointDistance.getDistanza());
		geometry.put("location", location);
		pointLocation.put("nome",point.getNome());
		pointLocation.put("citta",point.getCitta());
		pointLocation.put("stato", point.getStato());
		pointLocation.put("tipo", point.getTipo());
		pointLocation.put("id", point.getId());
		pointLocation.put("descrizione", point.getDescrizione());
		pointLocation.put("geometry",geometry);
		pointResponse.setEsito(true);
		pointResponse.setDescrizione("PUNTO PIU' VICINO TROVATO");
		results.add(pointLocation);
		pointResponse.setPointOfInterest(results);
		logger.debug(String.format("%s - %s::punto piu' vicino[%s]",CLASS,method,point.toString()));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::           END",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		return pointResponse;
	}


	
	public RemovePointResponse removePoint(int idPoint) {
		String method = "removePoint";
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::           START",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::input[%s]",CLASS,method,idPoint));
		
		RemovePointResponse pointResponse = new RemovePointResponse();
		PointDAO pointDAO = mysqlDAOfactory.getPointDAO();
		
		Point point = pointDAO.getPoinById(idPoint);
		
		if(point == null){
			pointResponse.setEsito(false);
			pointResponse.setDescrizione("PUNTO NON TROVATO");
			logger.debug(String.format("%s - %s::punto non trovato[null]",CLASS,method));
			logger.debug(String.format("%s - %s::*****************************",CLASS,method));
			logger.debug(String.format("%s - %s::           END",CLASS,method));
			logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		}
		if(pointDAO.removePointDAO(point)){
			pointResponse.setEsito(true);
			pointResponse.setDescrizione("PUNTO RIMOSSO CON SUCCESSO");			
		}else{
			pointResponse.setEsito(false);
			pointResponse.setDescrizione("NON E' STATO POSSIBILE RIMUOVERE IL PUNTO SELEZIONATO");	
		}
		
		
		logger.debug(String.format("%s - %s::rimozione punto[%s]",CLASS,method,pointResponse.toString()));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::           END",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		return pointResponse;
	}
	
	
	
	
	
}
