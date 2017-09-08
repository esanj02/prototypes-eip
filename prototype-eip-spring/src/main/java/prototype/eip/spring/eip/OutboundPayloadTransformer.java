package prototype.eip.spring.eip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


/**
 * 
 * @author Jide Esan
 *
 */
@Service("outboundPayloadTransformer")
public class OutboundPayloadTransformer {

	private static final Logger logger = LoggerFactory.getLogger(OutboundPayloadTransformer.class);
	
	
	@Autowired
	public OutboundPayloadTransformer(){
		super();
	}
	
	
	@Transformer
	public Object transform(Message<?> message) {
		logger.debug("transforming outbound {} event with message ID -> {}...", 
				message.getPayload().getClass().getSimpleName(), message.getHeaders().get("id"));
		
		return message;		
	}	
	
	
	
}