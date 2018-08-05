package next2me.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import next2me.abstracts.AbstractResponse;
import next2me.abstracts.AbstractRestController;
import next2me.bean.SessionBean;
import next2me.bean.User;
import next2me.command.ICommandFactory;
import next2me.enums.ErrorEnum;
import next2me.model.request.LoginRequest;
import next2me.model.request.RegisterRequest;
import next2me.model.response.LoginResponse;
import next2me.model.response.RegisterResponse;
import next2me.utils.ErrorHandler;

@RestController
@RequestMapping("user")
public class UserController extends AbstractRestController {
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private ICommandFactory commandFactory;
	
	@Autowired
	private SessionBean sessionBean;

	// metodo per creare un nuovo utente
	@CrossOrigin
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<AbstractResponse> createUser(@Valid @RequestBody RegisterRequest newUser, Errors errors) {
		
		String methodName = "createUser";
		
		logger.info("Start controller [" + methodName + "]");
		
		RegisterResponse output = new RegisterResponse();
		
		if (errors.hasErrors()) {
			output = ErrorHandler.addError(output, newUser, ErrorEnum.INPUT_NON_VALIDO);
		} else {
			output = (RegisterResponse) commandFactory.callService("registrazioneService", newUser, output);
			if (output.getToken_sessione() != null) {
				User currentUser = new User();
				currentUser.setToken_sessione(output.getToken_sessione());
				currentUser.setEmail(newUser.getEmail());
				currentUser.setName(newUser.getName());
				this.sessionBean.setUser(currentUser);	
			}
		}
		
		logger.info("End controller [" + methodName + "]");
		return this.buildResponse(newUser, output);

	}

	// metodo per la login
	@CrossOrigin
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<AbstractResponse> login(@Valid @RequestBody LoginRequest loginUser, Errors errors) {

		
		String methodName = "loginUser";
		
		logger.info("Start controller [" + methodName + "]");
		
		LoginResponse output = new LoginResponse();
		
		if (errors.hasErrors()) {
			output = ErrorHandler.addError(output, loginUser, ErrorEnum.INPUT_NON_VALIDO);
		} else {
			output = (LoginResponse) commandFactory.callService("loginService", loginUser, output);
			if (output.getToken_sessione() != null) {
				User currentUser = new User();
				currentUser.setToken_sessione(output.getToken_sessione());
				currentUser.setEmail(loginUser.getEmail());
				currentUser.setName(output.getNome());
				this.sessionBean.setUser(currentUser);	
			}
		}
		
		logger.info("End controller [" + methodName + "]");
		
		return this.buildResponse(loginUser, output);

	}
	
	/*il metodo è utilizzato per controllare se l'utente è attualmente loggato o è scaduta la sessione
	 * se si è loggati restituisce un token sessione*/
	@CrossOrigin
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> logout(){
		this.sessionBean.setUser(null);
		return new ResponseEntity<>("", HttpStatus.OK);
	}

	
	/*il metodo è utilizzato per controllare se l'utente è attualmente loggato o è scaduta la sessione
	 * se si è loggati restituisce un token sessione*/
	@CrossOrigin
	@RequestMapping(value = "/isLogged", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> isLogged(){

		if (this.sessionBean.getUser() != null) {
			this.displaySessionInfo();
			return new ResponseEntity<>(this.sessionBean.getUser().getToken_sessione(), HttpStatus.OK);	
		} else {
			return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);	
		}

	}

	private void displaySessionInfo(){
		logger.info("Session info [" + this.sessionBean.getUser().toString() + "]");	
	}

}
