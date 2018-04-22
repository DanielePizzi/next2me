package next2me.controllers;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import next2me.abstracts.AbstractRequest;
import next2me.abstracts.AbstractResponse;
import next2me.abstracts.AbstractRestController;
import next2me.command.ICommandFactory;
import next2me.model.request.TestInput;
import next2me.model.response.TestOutput;

@RestController
@RequestMapping("user")
public class UserController extends AbstractRestController {
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

//	@Autowired
//	private UserService userService;
	
	@Autowired
	@Qualifier("commandFactory")
	private ICommandFactory commandFactory;

	// metodo per creare un nuovo utente
	@CrossOrigin
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody TestInput newUser) {
//		if (userService.find(newUser.getUsername()) != null) {
//			logger.error("username Already exist " + newUser.getUsername());
//			return new ResponseEntity(
//					new CustomErrorType("user with username " + newUser.getUsername() + "already exist "),
//					HttpStatus.CONFLICT);
//		}
//		
//		User output = null;
		
		AbstractResponse output = new TestOutput();
		output = commandFactory.callService("userService", newUser, output);
//		
		return this.buildResponse(newUser, output);
//		
//		newUser.setRole("USER");
		//
		
//		return new ResponseEntity<User>(userService.save(newUser), HttpStatus.CREATED);
//		return new ResponseEntity<Object>(output, HttpStatus.CREATED);
	}

	// this is the login api/service
	@CrossOrigin
	@RequestMapping("/login")
	public Principal user(Principal principal) {
		logger.info("user logged "+principal);
		return principal;
	}

}
