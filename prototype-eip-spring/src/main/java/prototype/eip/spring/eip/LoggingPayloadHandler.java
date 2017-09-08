package prototype.eip.spring.eip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;



/**
 * 
 * @author Jide Esan
 *
 */
@Service("loggingPayloadHandler")
public class LoggingPayloadHandler {

	private static final Logger logger = LoggerFactory.getLogger(LoggingPayloadHandler.class);
	
	
	@ServiceActivator
	public Object handleLogging(Message<?> message) throws MessagingException {
		logger.debug("");
		
		System.err.println(getClass().getSimpleName());
		
		return message;
	}
	
		
}
