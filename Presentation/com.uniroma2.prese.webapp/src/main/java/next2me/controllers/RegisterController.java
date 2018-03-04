package next2me.controllers;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import next2me.model.request.RegisterRequest;
import next2me.model.response.RegisterResponse;
import next2me.services.IServices;
import next2me.services.impl.ServicesImpl;
import next2me.utils.SessionToken;


@RestController
public class RegisterController {
	
	private static final String CLASS = "RegisterController";
	
	Logger logger = Logger.getLogger("FINDMENEAR");
	
	private IServices services = new ServicesImpl();

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public RegisterResponse register(@Valid @RequestBody RegisterRequest registrazione, Errors errors){
		String method = "register";
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::           START",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		
		logger.debug(String.format("%s - %s::input[%s]",CLASS,method,registrazione.toString()));
		
		RegisterResponse response = new RegisterResponse();
		
		if(errors.hasErrors()){
			response.setEsito(false);
			response.setDescrizione("INPUT NON VALIDI");
			logger.debug(String.format("%s - %s:: errore nei parametri di input - response[%s]",CLASS,method,response.toString()));
			return response;
		}
		
		String name = registrazione.getName();
		String email = registrazione.getEmail();
		String password = registrazione.getPassword();
		if(!services.isUserExist(email,name)){
			services.registrazione(name, email, password);
			response.setEsito(true);
			response.setDescrizione("REGISTRAZIONE AVVENUTA CON SUCCESSO");
			response.setToken_sessione(SessionToken.getSessionToken());
			response.setNome(name);
			logger.debug(String.format("%s - %s::response[%s]",CLASS,method,response.toString()));
			logger.debug(String.format("%s - %s::*****************************",CLASS,method));
			logger.debug(String.format("%s - %s::           END",CLASS,method));
			logger.debug(String.format("%s - %s::*****************************",CLASS,method));
			return response;
		}
		response.setEsito(false);
		response.setDescrizione("USERNAME O MAIL GIA' ESISTENTI");
		logger.debug(String.format("%s - %s:: l'utente esiste - response[%s]",CLASS,method,response.toString()));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::           END",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		return response;
	}
}
