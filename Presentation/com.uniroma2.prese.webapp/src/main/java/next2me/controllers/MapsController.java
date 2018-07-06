package next2me.controllers;

import java.util.HashMap;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import next2me.abstracts.AbstractResponse;
import next2me.abstracts.AbstractRestController;
import next2me.command.ICommandFactory;
import next2me.enums.ErrorEnum;
import next2me.model.request.GetPointRequest;
import next2me.model.request.RemovePointRequest;
import next2me.model.request.SavePointRequest;
import next2me.model.response.GetPointResponse;
import next2me.model.response.RegisterResponse;
import next2me.model.response.RemovePointResponse;
import next2me.model.response.SavePointResponse;
import next2me.utils.ErrorHandler;


@RestController
@RequestMapping("maps")
public class MapsController extends AbstractRestController{
	
	@Autowired
	private ICommandFactory commandFactory;
	
	private static final String CLASS = "MapsController";
	public static final Logger logger = LoggerFactory.getLogger(MapsController.class);

	@RequestMapping(value = "/salvaPuntoInteresse", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<AbstractResponse> salvaPuntoInteresse(@Valid @RequestBody SavePointRequest request, Errors errors){
		
		String methodName = "salvaPuntoInteresse";
		
		logger.info("Start controller [" + methodName + "]");
		
		SavePointResponse output = new SavePointResponse();
		
		if (errors.hasErrors()) {
			output = ErrorHandler.addError(output, request, ErrorEnum.INPUT_NON_VALIDO);
		} else {
			output = (SavePointResponse) commandFactory.callService("salvaPuntoInteresseService", request, output);
		}
		
		logger.info("End controller [" + methodName + "]");
		
		return this.buildResponse(request, output);
		
	}
	
	@RequestMapping(value = "/getPuntoInteresse", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<AbstractResponse> getPuntoInteresse(@Valid @RequestBody GetPointRequest request, Errors errors){
		
		String methodName = "salvaPuntoInteresse";
		
		logger.info("Start controller [" + methodName + "]");
		
		GetPointResponse output = new GetPointResponse();
		
		if (errors.hasErrors()) {
			output = ErrorHandler.addError(output, request, ErrorEnum.INPUT_NON_VALIDO);
		} else {
			output = (GetPointResponse) commandFactory.callService("getPuntoInteresseService", request, output);
		}
		
		logger.info("End controller [" + methodName + "]");
		
		return this.buildResponse(request, output);
		
	}
	
	
	@RequestMapping(value = "/rimuoviPuntoInteresse", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<AbstractResponse> rimuoviPuntoInteresse(@Valid @RequestBody RemovePointRequest request, Errors errors){
		
		String methodName = "rimuoviPuntoInteresse";
		
		logger.info("Start controller [" + methodName + "]");
		
		RemovePointResponse output = new RemovePointResponse();
		
		if (errors.hasErrors()) {
			output = ErrorHandler.addError(output, request, ErrorEnum.INPUT_NON_VALIDO);
		} else {
			output = (RemovePointResponse) commandFactory.callService("rimuoviPuntoInteresseService", request, output);
		}
		
		logger.info("End controller [" + methodName + "]");
		
		return this.buildResponse(request, output);
		
	}
}
