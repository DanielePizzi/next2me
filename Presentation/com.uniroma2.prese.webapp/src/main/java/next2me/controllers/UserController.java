package next2me.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.security.Principal;
import next2me.abstracts.*;
import next2me.command.ICommandFactory;

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
	public ResponseEntity<?> createUser(@RequestBody Object newUser) {
//		if (userService.find(newUser.getUsername()) != null) {
//			logger.error("username Already exist " + newUser.getUsername());
//			return new ResponseEntity(
//					new CustomErrorType("user with username " + newUser.getUsername() + "already exist "),
//					HttpStatus.CONFLICT);
//		}
//		
//		User output = null;
		
		AbstractRequest input = null;
		AbstractResponse output = null;
		Object test = commandFactory.callService("userService", input, output);
//		
//		return this.buildResponse(output, newUser);
//		
//		newUser.setRole("USER");
		//
		
//		return new ResponseEntity<User>(userService.save(newUser), HttpStatus.CREATED);
		return new ResponseEntity<Object>(null, HttpStatus.CREATED);
	}

	// this is the login api/service
	@CrossOrigin
	@RequestMapping("/login")
	public Principal user(Principal principal) {
		logger.info("user logged "+principal);
		return principal;
	}

}
