package next2me.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import next2me.abstracts.AbstractMapper;
import next2me.abstracts.AbstractRequest;
import next2me.abstracts.AbstractResponse;
import next2me.enums.ErrorEnum;
import next2me.model.request.RegisterRequest;
import next2me.model.response.RegisterResponse;
import next2me.persistent.dao.UserDAO;
import next2me.persistent.daoFactory.DAOFactory;
import next2me.persistent.entity.User;
import next2me.services.AbstractService;
import next2me.utils.ErrorHandler;
import next2me.utils.SessionToken;

@Service
public class RegistrazioneService extends AbstractService {
	
	private static final Logger logger = LoggerFactory.getLogger(RegistrazioneService.class);
	private static final String CLASS = "RegistrazioneService";
	private static DAOFactory mysqlDAOfactory = getDao();
	
	@Autowired
	private AbstractMapper registrazioneMapper;

	@Override
	public AbstractResponse execute(AbstractRequest request, AbstractResponse response) {
		
		String methodName = "execute";
		
		logger.info("Start service [" + methodName + "]. Input: " + request.toString());
		
		RegisterResponse out = (RegisterResponse) response;
		RegisterRequest req = (RegisterRequest) request;
		
		logger.debug(String.format("%s - %s::*****************************",CLASS,methodName));
		logger.debug(String.format("%s - %s::           START",CLASS,methodName));
		logger.debug(String.format("%s - %s::*****************************",CLASS,methodName));
		
		logger.debug(String.format("%s - %s::input[%s]",CLASS,methodName,req.toString()));
		
		if(!this.isUserExist(req)){
			this.registrazione(req);
			out.setToken_sessione(SessionToken.getSessionToken());
			logger.debug(String.format("%s - %s::response[%s]",CLASS,methodName,response.toString()));
			logger.debug(String.format("%s - %s::*****************************",CLASS,methodName));
			logger.debug(String.format("%s - %s::           END",CLASS,methodName));
			logger.debug(String.format("%s - %s::*****************************",CLASS,methodName));
		} else {
			out = (RegisterResponse) ErrorHandler.addError(out, req, ErrorEnum.UTENTE_ESISTENTE);
			logger.debug(String.format("%s - %s:: l'utente esiste - response[%s]",CLASS,methodName,response.toString()));
			logger.debug(String.format("%s - %s::*****************************",CLASS,methodName));
			logger.debug(String.format("%s - %s::           END",CLASS,methodName));
			logger.debug(String.format("%s - %s::*****************************",CLASS,methodName));
		}

		logger.info("End service [" + methodName + "]. Output: " + response.toString());
		
		return out;
	}
	
	public void registrazione(RegisterRequest req){
		String method = "registrazione";
		
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::           START",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		
		UserDAO userDAO = mysqlDAOfactory.getUserDAO();
		
		User user = (User) registrazioneMapper.marshall(req);
		userDAO.addUser(user);
		
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::           END",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
	}
	
	private boolean isUserExist(RegisterRequest req) {
		String method = "isUserExist";
		
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::           START",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		
		UserDAO userDAO = mysqlDAOfactory.getUserDAO();
		
		User user = userDAO.getUser(req.getEmail());
		
		if(user != null){
			
			logger.debug(String.format("%s - %s:: l'utente esiste [%s]",CLASS,method,user.toString()));
			logger.debug(String.format("%s - %s:: return true",CLASS,method));
			logger.debug(String.format("%s - %s::*****************************",CLASS,method));
			logger.debug(String.format("%s - %s::           END",CLASS,method));
			logger.debug(String.format("%s - %s::*****************************",CLASS,method));
			return true;
		}
		
		User userName = userDAO.getUserName(req.getName());
		
		if(userName != null){
			logger.debug(String.format("%s - %s:: esiste un utente con lo stesso nome[%s]",CLASS,method,userName.toString()));
			logger.debug(String.format("%s - %s:: return true",CLASS,method));
			logger.debug(String.format("%s - %s::*****************************",CLASS,method));
			logger.debug(String.format("%s - %s::           END",CLASS,method));
			logger.debug(String.format("%s - %s::*****************************",CLASS,method));
			return true;
		}
		
		logger.debug(String.format("%s - %s:: l'utente non esiste",CLASS,method));
		logger.debug(String.format("%s - %s:: return false",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::           END",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		return false;
		
	}

}
