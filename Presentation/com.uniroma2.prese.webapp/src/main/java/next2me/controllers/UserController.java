package next2me.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import next2me.abstracts.AbstractRequest;
import next2me.abstracts.AbstractResponse;
import next2me.abstracts.AbstractRestController;
import next2me.command.ICommandFactory;
import next2me.enums.ErrorEnum;
import next2me.model.request.LoginRequest;
import next2me.model.request.RegisterRequest;
import next2me.model.request.TestInput;
import next2me.model.response.LoginResponse;
import next2me.model.response.RegisterResponse;
import next2me.model.response.TestOutput;
import next2me.utils.ErrorHandler;

@RestController
@RequestMapping("user")
public class UserController extends AbstractRestController {
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private ICommandFactory commandFactory;

	// metodo per creare un nuovo utente
	@CrossOrigin
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<AbstractResponse> createUser(@Valid @RequestBody RegisterRequest newUser, Errors errors) {
//		if (userService.find(newUser.getUsername()) != null) {
//			logger.error("username Already exist " + newUser.getUsername());
//			return new ResponseEntity(
//					new CustomErrorType("user with username " + newUser.getUsername() + "already exist "),
//					HttpStatus.CONFLICT);
//		}
//		
//		User output = null;
		
		String methodName = "createUser";
		
		logger.info("Start controller [" + methodName + "]");
		
		RegisterResponse output = new RegisterResponse();
		
		if (errors.hasErrors()) {
			output = ErrorHandler.addError(output, newUser, ErrorEnum.INPUT_NON_VALIDO);
		} else {
			output = (RegisterResponse) commandFactory.callService("registrazioneService", newUser, output);
		}
		
		logger.info("End controller [" + methodName + "]");
		
		return this.buildResponse(newUser, output);
//		
//		newUser.setRole("USER");
		//
		
//		return new ResponseEntity<User>(userService.save(newUser), HttpStatus.CREATED);
//		return new ResponseEntity<Object>(output, HttpStatus.CREATED);
	}

	// metodo per la login
	@CrossOrigin
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<AbstractResponse> login(@Valid @RequestBody LoginRequest loginUser, Errors errors) {

//		logger.info("user logged "+principal);
//		return principal;
		
		String methodName = "loginUser";
		
		logger.info("Start controller [" + methodName + "]");
		
		LoginResponse output = new LoginResponse();
		
		if (errors.hasErrors()) {
			output = ErrorHandler.addError(output, loginUser, ErrorEnum.INPUT_NON_VALIDO);
		} else {
			output = (LoginResponse) commandFactory.callService("loginService", loginUser, output);
		}
		
		logger.info("End controller [" + methodName + "]");
		
		return this.buildResponse(loginUser, output);
//		
//		newUser.setRole("USER");
		//
		
//		return new ResponseEntity<User>(userService.save(newUser), HttpStatus.CREATED);
//		return new ResponseEntity<Object>(output, HttpStatus.CREATED);
	}

}
