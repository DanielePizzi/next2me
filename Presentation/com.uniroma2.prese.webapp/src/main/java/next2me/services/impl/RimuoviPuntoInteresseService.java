package next2me.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import next2me.abstracts.AbstractRequest;
import next2me.abstracts.AbstractResponse;
import next2me.enums.ErrorEnum;
import next2me.model.request.GetPointRequest;
import next2me.model.request.RemovePointRequest;
import next2me.model.response.GetPointResponse;
import next2me.model.response.RemovePointResponse;
import next2me.persistent.dao.PointDAO;
import next2me.persistent.daoFactory.DAOFactory;
import next2me.persistent.entity.Point;
import next2me.services.AbstractService;
import next2me.utils.ErrorHandler;

@Service
public class RimuoviPuntoInteresseService extends AbstractService{
	
	private static final Logger logger = LoggerFactory.getLogger(RimuoviPuntoInteresseService.class);
	private static final String CLASS = "LoginService";
	private static DAOFactory mysqlDAOfactory = getDao();

	@Override
	public AbstractResponse execute(AbstractRequest request, AbstractResponse response) {
		
		String methodName = "removePoint";
		
		logger.info("Start service [" + methodName + "]. Input: " + request.toString());

		RemovePointResponse out = (RemovePointResponse) response;
		RemovePointRequest req = (RemovePointRequest) request;
		
		logger.debug(String.format("%s - %s::*****************************",CLASS,methodName));
		logger.debug(String.format("%s - %s::           START",CLASS,methodName));
		logger.debug(String.format("%s - %s::*****************************",CLASS,methodName));
		
		logger.debug(String.format("%s - %s::input[%s]",CLASS,methodName,request.toString()));
		
		try{
			out = this.removePoint(req,out);
		}catch(Exception e){
			logger.debug(String.format("%s - %s::errors[%s]",CLASS,methodName,e));
			out = (RemovePointResponse) ErrorHandler.addError(out, req, ErrorEnum.ERRORE_INTERNO);
		}
 		logger.debug(String.format("%s - %s::*****************************",CLASS,methodName));
		logger.debug(String.format("%s - %s::           END",CLASS,methodName));
		logger.debug(String.format("%s - %s::*****************************",CLASS,methodName));
		return out;
	}
	
	private RemovePointResponse removePoint(RemovePointRequest req, RemovePointResponse out) {
		String methodName = "removePoint";
		logger.debug(String.format("%s - %s::*****************************",CLASS,methodName));
		logger.debug(String.format("%s - %s::           START",CLASS,methodName));
		logger.debug(String.format("%s - %s::*****************************",CLASS,methodName));
		logger.debug(String.format("%s - %s::input[%s]",CLASS,methodName,req.getIdPoint()));
		
		PointDAO pointDAO = mysqlDAOfactory.getPointDAO();
		
		Point point = pointDAO.getPoinById(req.getIdPoint());
		
		if(point == null){
			out = (RemovePointResponse) ErrorHandler.addError(out, req, ErrorEnum.PUNTO_NON_TROVATO);
			logger.debug(String.format("%s - %s::punto non trovato[null]",CLASS,methodName));
			logger.debug(String.format("%s - %s::*****************************",CLASS,methodName));
			logger.debug(String.format("%s - %s::           END",CLASS,methodName));
			logger.debug(String.format("%s - %s::*****************************",CLASS,methodName));
		} else {
			if(!pointDAO.removePointDAO(point)){
				out = (RemovePointResponse) ErrorHandler.addError(out, req, ErrorEnum.PUNTO_NON_RIMOSSO);	
			}
		}
		
		logger.debug(String.format("%s - %s::rimozione punto[%s]",CLASS,methodName,out.toString()));
		logger.debug(String.format("%s - %s::*****************************",CLASS,methodName));
		logger.debug(String.format("%s - %s::           END",CLASS,methodName));
		logger.debug(String.format("%s - %s::*****************************",CLASS,methodName));
		return out;
	}

}
