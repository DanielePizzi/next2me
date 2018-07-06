package next2me.services.impl;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import next2me.abstracts.AbstractRequest;
import next2me.abstracts.AbstractResponse;
import next2me.enums.ErrorEnum;
import next2me.model.request.RegisterRequest;
import next2me.model.request.SavePointRequest;
import next2me.model.response.GetPointResponse;
import next2me.model.response.RegisterResponse;
import next2me.model.response.SavePointResponse;
import next2me.persistent.dao.PointDAO;
import next2me.persistent.dao.UserDAO;
import next2me.persistent.daoFactory.DAOFactory;
import next2me.persistent.entity.Point;
import next2me.persistent.entity.User;
import next2me.services.AbstractService;
import next2me.utils.ErrorHandler;

@Service
public class SalvaPuntoInteresseService extends AbstractService {
	
	private static final Logger logger = LoggerFactory.getLogger(SalvaPuntoInteresseService.class);
	private static final String CLASS = "LoginService";
	private static DAOFactory mysqlDAOfactory = getDao();

	@Override
	public AbstractResponse execute(AbstractRequest request, AbstractResponse response) {
		String methodName = "execute";
		
		logger.info("Start service [" + methodName + "]. Input: " + request.toString());
		
		SavePointResponse out = (SavePointResponse) response;
		SavePointRequest req = (SavePointRequest) request;
		
		logger.debug(String.format("%s - %s::*****************************",CLASS,methodName));
		logger.debug(String.format("%s - %s::           START",CLASS,methodName));
		logger.debug(String.format("%s - %s::*****************************",CLASS,methodName));
		
		logger.debug(String.format("%s - %s::input[%s]",CLASS,methodName,request.toString()));
		
		try{
//			out = this.savePoint(request.getUsername(), nome, citta, stato, lat, lng, tipo, descrizione);
			this.savePoint(req, out);
		}catch(Exception e){
			logger.debug(String.format("%s - %s::errors[%s]",CLASS,methodName,e));
			out = (SavePointResponse) ErrorHandler.addError(out, req, ErrorEnum.ERRORE_INTERNO);
		}
 		logger.debug(String.format("%s - %s::*****************************",CLASS,methodName));
		logger.debug(String.format("%s - %s::           END",CLASS,methodName));
		logger.debug(String.format("%s - %s::*****************************",CLASS,methodName));
		return out;
	}
	
	private void savePoint(SavePointRequest req, SavePointResponse out) {
		String method = "savePoint";
		
		HashMap<String,Object> pointLocation = ((HashMap<String, Object>) req.getPointOfInterest());
		String nome = (String) pointLocation.get("nome");
		String citta = (String) pointLocation.get("citta");
		String stato = (String) pointLocation.get("stato");
		HashMap<String,Object> geometry = (HashMap<String, Object>) pointLocation.get("geometry");
		HashMap<String,Object> location = (HashMap<String, Object>) geometry.get("location");
		String lat = location.get("lat").toString();
		String lng = location.get("lng").toString();
		String tipo = (String) pointLocation.get("tipo");
		String descrizione = (String) pointLocation.get("descrizione");
		
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::           START",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::  - parametri in INPUT - inizio elenco",CLASS,method));
		logger.debug(String.format("%s - %s::username[%s]",CLASS,method,req.getUsername()));
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
		
		User user = userDAO.getUserName(req.getUsername());
		
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

}
