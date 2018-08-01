package next2me.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import next2me.abstracts.AbstractRequest;
import next2me.abstracts.AbstractResponse;
import next2me.enums.ErrorEnum;
import next2me.model.request.LoginRequest;
import next2me.model.request.RegisterRequest;
import next2me.model.response.LoginResponse;
import next2me.model.response.RegisterResponse;
import next2me.persistent.dao.UserDAO;
import next2me.persistent.daoFactory.DAOFactory;
import next2me.persistent.entity.User;
import next2me.services.AbstractService;
import next2me.utils.CryptPassword;
import next2me.utils.ErrorHandler;
import next2me.utils.SessionToken;

@Service
public class LoginService extends AbstractService{
	
	private static final Logger logger = LoggerFactory.getLogger(LoginService.class);
	private static final String CLASS = "LoginService";
	private static DAOFactory mysqlDAOfactory = getDao();

	@Override
	public AbstractResponse execute(AbstractRequest request, AbstractResponse response) {

		String methodName = "execute";
		
		logger.info("Start service [" + methodName + "]. Input: " + request.toString());
		
		LoginResponse out = (LoginResponse) response;
		LoginRequest req = (LoginRequest) request;
		
		logger.debug(String.format("%s - %s::*****************************",CLASS,methodName));
		logger.debug(String.format("%s - %s::           START",CLASS,methodName));
		logger.debug(String.format("%s - %s::*****************************",CLASS,methodName));
		
		logger.debug(String.format("%s - %s::input[%s]",CLASS,methodName,req.toString()));
		
		String email = req.getEmail();
		String password = req.getPassword();
		
		User result = this.login(email);
		if(result == null){
			out = (LoginResponse) ErrorHandler.addError(out, req, ErrorEnum.UTENTE_NON_ESISTENTE);
			logger.debug(String.format("%s - %s::user null",CLASS,methodName));
			logger.debug(String.format("%s - %s::response[%s]",CLASS,methodName,response.toString()));
			logger.debug(String.format("%s - %s::*****************************",CLASS,methodName));
			logger.debug(String.format("%s - %s::           END",CLASS,methodName));
			logger.debug(String.format("%s - %s::*****************************",CLASS,methodName));
		} else if (CryptPassword.cryptWithMD5(password).equals(result.getPassword())) {
			out.setToken_sessione(SessionToken.getSessionToken());
			logger.debug(String.format("%s - %s::user[%s]",CLASS,methodName,result.toString()));
			logger.debug(String.format("%s - %s::response[%s]",CLASS,methodName,response.toString()));
			logger.debug(String.format("%s - %s::*****************************",CLASS,methodName));
			logger.debug(String.format("%s - %s::           END",CLASS,methodName));
			logger.debug(String.format("%s - %s::*****************************",CLASS,methodName));
		} else {
			out = (LoginResponse) ErrorHandler.addError(out, req, ErrorEnum.PASSWORD_NON_CORRETTA);
			logger.debug(String.format("%s - %s::response[%s]",CLASS,methodName,response.toString()));
			logger.debug(String.format("%s - %s::*****************************",CLASS,methodName));
			logger.debug(String.format("%s - %s::           END",CLASS,methodName));
			logger.debug(String.format("%s - %s::*****************************",CLASS,methodName));
		}
		return out;
	}
	
	public User login(String email){
		String methodName = "login";
		logger.debug(String.format("%s - %s::*****************************",CLASS,methodName));
		logger.debug(String.format("%s - %s::           START",CLASS,methodName));
		logger.debug(String.format("%s - %s::*****************************",CLASS,methodName));
		
		UserDAO userDAO = mysqlDAOfactory.getUserDAO();
			
		User user = userDAO.getUser(email);

		if (user != null){
			logger.debug(String.format("%s - %s:: user[%s]",CLASS,methodName,user.toString()));			
		}
		logger.debug(String.format("%s - %s::*****************************",CLASS,methodName));
		logger.debug(String.format("%s - %s::           END",CLASS,methodName));
		logger.debug(String.format("%s - %s::*****************************",CLASS,methodName));
		
		return user;
	}


}
