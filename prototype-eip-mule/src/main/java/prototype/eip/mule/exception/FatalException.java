package prototype.eip.mule.exception;

import org.mule.api.MuleException;
import org.mule.config.i18n.MessageFactory;


/**
 * 
 * @author Jide Esan
 *
 */
public class FatalException extends MuleException {

	private static final long serialVersionUID = 4090762835464831276L;

	
	/**
	 * @param abstractMessage
	 */
	public FatalException(String message) {
		this("FATAL EXCEPTION: " + message, null);
	}

	/**
	 * @param abstractMessage
	 * @param cause
	 */
	public FatalException(String message, Throwable cause) {
		super(MessageFactory.createStaticMessage(message), cause);
	}

	
}
