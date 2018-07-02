package next2me.mappers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import next2me.abstracts.AbstractMapper;
import next2me.model.request.RegisterRequest;
import next2me.persistent.entity.User;

@Qualifier
@Component
public class RegistrazioneMapper extends AbstractMapper {
	
	private static final Logger logger = LoggerFactory.getLogger(RegistrazioneMapper.class);

	@Override
	public Object marshall(Object input) {
		
		String methodName = "RegistrazioneMapper";

		User out = new User();
		RegisterRequest req = (RegisterRequest) input;
		
		logger.info("Start mapper [" + methodName + "]. Input: " + req.toString());
		
		out.setNome(req.getName());
		out.setEmail(req.getEmail());
		out.setPassword(req.getPassword());
		
		logger.info("End mapper [" + methodName + "]. Output: " + out.toString());
		
		return out;
	}

	@Override
	public Object unMarshall(Object output) {
		// TODO Auto-generated method stub
		return null;
	}

}
