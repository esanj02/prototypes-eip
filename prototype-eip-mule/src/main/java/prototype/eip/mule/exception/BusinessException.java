package prototype.eip.mule.exception;

import org.mule.api.MuleException;
import org.mule.config.i18n.MessageFactory;


/**
 * 
 * @author Jide Esan
 *
 */
public class BusinessException extends MuleException {

	private static final long serialVersionUID = -4272097228291000045L;

	/**
	 * @param abstractMessage
	 */
	public BusinessException(String message) {
		this("BUSINESS EXCEPTION: " + message, null);
	}

	/**
	 * @param abstractMessage
	 * @param cause
	 */
	public BusinessException(String message, Throwable cause) {
		super(MessageFactory.createStaticMessage(message), cause);
	}

}
