package prototype.eip.spring.eip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;


/**
 * 
 * @author Jide Esan
 *
 */
@Service("errorPayloadHandler")
public class ErrorPayloadHandler implements ErrorHandler {

	private static final Logger logger = LoggerFactory.getLogger(ErrorPayloadHandler.class);
	
	@Autowired
	@Qualifier("outboundErrorPayloadHandlerChannel")
	private MessageChannel outboundErrorPayloadHandlerChannel;
	
	@Autowired
	public ErrorPayloadHandler() {}
	
	
	/**
	 * {@inheritDoc}
	 * @see org.springframework.util.ErrorHandler#handleError(java.lang.Throwable)
	 */
	@ServiceActivator
	public void handleError(Throwable throwable) {
		logger.debug("handling ERROR event -> {}...", throwable.getMessage());
	
		System.err.println(getClass().getSimpleName());
		
		final MessagingException messagingException = (MessagingException) throwable;
		final Message<MessagingException> errorMessage = MessageBuilder
				.withPayload(messagingException)
				.copyHeaders(messagingException.getFailedMessage().getHeaders())
				.setHeader("failed-message", messagingException.getFailedMessage())
				.build();
		
		outboundErrorPayloadHandlerChannel.send(errorMessage);
	}

	
	
}
