package prototype.eip.spring.eip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;


/**
 * 
 * @author Jide Esan
 *
 */
@Service("outboundErrorPayloadHandler")
public class OutboundErrorPayloadHandler {
	
	private final static Logger logger = LoggerFactory.getLogger(OutboundErrorPayloadHandler.class);

	@Autowired
	@Qualifier("outboundErrorPayloadChannel")
	private MessageChannel outboundErrorPayloadChannel;

	
	public OutboundErrorPayloadHandler(){
		super();
	}
	
		
	@ServiceActivator
	public Object handleErrorPayload(Message<?> message) throws Exception {
		logger.debug("handling outbound ERROR {} event...", 
				message.getPayload().getClass().getSimpleName());
				
				
		return message;
	}
		
	
}
