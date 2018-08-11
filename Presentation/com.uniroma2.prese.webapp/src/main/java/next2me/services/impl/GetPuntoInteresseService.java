package next2me.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import next2me.abstracts.AbstractRequest;
import next2me.abstracts.AbstractResponse;
import next2me.enums.ErrorEnum;
import next2me.model.request.GetPointRequest;
import next2me.model.request.RegisterRequest;
import next2me.model.response.GetPointResponse;
import next2me.model.response.RegisterResponse;
import next2me.model.utils.PointDistance;
import next2me.persistent.dao.PointDAO;
import next2me.persistent.dao.UserDAO;
import next2me.persistent.daoFactory.DAOFactory;
import next2me.persistent.entity.Point;
import next2me.persistent.entity.User;
import next2me.services.AbstractService;
import next2me.utils.ErrorHandler;

@Service
public class GetPuntoInteresseService extends AbstractService{
	
	private static final Logger logger = LoggerFactory.getLogger(GetPuntoInteresseService.class);
	private static final String CLASS = "GetPuntoInteresseService";
	private static DAOFactory mysqlDAOfactory = getDao();

	@Override
	public AbstractResponse execute(AbstractRequest request, AbstractResponse response) {
		
		String methodName = "execute";
		
		logger.info("Start service [" + methodName + "]. Input: " + request.toString());
		
		GetPointResponse out = (GetPointResponse) response;
		GetPointRequest req = (GetPointRequest) request;
		
		logger.debug(String.format("%s - %s::*****************************",CLASS,methodName));
		logger.debug(String.format("%s - %s::           START",CLASS,methodName));
		logger.debug(String.format("%s - %s::*****************************",CLASS,methodName));
		
		logger.debug(String.format("%s - %s::input[%s]",CLASS,methodName,request.toString()));
		
		try{
//			out = this.getPoint(req.getUsername(),req.getCategoria(), Double.parseDouble(req.getLatitudine()), Double.parseDouble(req.getLongitudine()));
			out = this.getPoint(req,out);
		}catch(Exception e){
			logger.debug(String.format("%s - %s::errors[%s]",CLASS,methodName,e));
			out = (GetPointResponse) ErrorHandler.addError(out, req, ErrorEnum.ERRORE_INTERNO);
		}
 		logger.debug(String.format("%s - %s::*****************************",CLASS,methodName));
		logger.debug(String.format("%s - %s::           END",CLASS,methodName));
		logger.debug(String.format("%s - %s::*****************************",CLASS,methodName));
		return out;
	}
	
	private GetPointResponse getPoint(GetPointRequest req, GetPointResponse out) {
		String method = "getPoint";
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::           START",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		
		PointDAO pointDAO = mysqlDAOfactory.getPointDAO();
		UserDAO userDAO = mysqlDAOfactory.getUserDAO();
		ArrayList<Map> results = new ArrayList<Map>();
		User user = userDAO.getUserName(req.getUsername());
		
		if(user == null){
			logger.debug(String.format("%s-%s:: user null",CLASS,method));
			out = (GetPointResponse) ErrorHandler.addError(out, req, ErrorEnum.UTENTE_NON_ESISTENTE);
		} else {
			ArrayList<PointDistance> pointDistance = pointDAO.getPointNear(user.getId(),req.getCategoria(), Double.parseDouble(req.getLatitudine()), Double.parseDouble(req.getLongitudine()));
			
			if(pointDistance == null){
				logger.debug(String.format("%s - %s::point null",CLASS,method));
				out = (GetPointResponse) ErrorHandler.addError(out, req, ErrorEnum.PUNTI_INTERESSE_NON_ESISTENTI);
			} else {
				for (PointDistance pointDistanceIterator : pointDistance) {
					HashMap<String,Object> pointLocation = new HashMap<String, Object>();
					HashMap<String,Object> geometry = new HashMap<String, Object>();
					HashMap<String,Object> location = new HashMap<String, Object>();
					Point point = pointDistanceIterator.getPoint();	
					logger.debug(String.format("%s-%s:: punto da restituire[%s]",CLASS,method,point.toString()));
					location.put("lat", Double.parseDouble(point.getLat()));
					location.put("lng", Double.parseDouble(point.getLng()));
					location.put("distanza", pointDistanceIterator.getDistanza());
					geometry.put("location", location);
					pointLocation.put("nome",point.getNome());
					pointLocation.put("citta",point.getCitta());
					pointLocation.put("stato", point.getStato());
					pointLocation.put("tipo", point.getTipo());
					pointLocation.put("id", point.getId());
					pointLocation.put("descrizione", point.getDescrizione());
					pointLocation.put("geometry",geometry);
					results.add(pointLocation);
				}	
				out.setPointOfInterest(results);
//				logger.debug(String.format("%s - %s::punto piu' vicino[%s]",CLASS,method,point.toString()));
				logger.debug(String.format("%s - %s::*****************************",CLASS,method));
				logger.debug(String.format("%s - %s::           END",CLASS,method));
				logger.debug(String.format("%s - %s::*****************************",CLASS,method));
			}
		}
		return out;
	}

}
