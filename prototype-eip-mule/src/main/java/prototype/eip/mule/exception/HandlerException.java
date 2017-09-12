package prototype.eip.mule.exception;

import org.mule.api.MuleException;
import org.mule.config.i18n.MessageFactory;


/**
 * 
 * @author Jide Esan
 *
 */
public class HandlerException extends MuleException {

	private static final long serialVersionUID = -7925776537576225704L;
	

	public HandlerException(String message) {
		super(MessageFactory.createStaticMessage(message));
	}

	public HandlerException(String message, Throwable cause) {
		super(MessageFactory.createStaticMessage(message), cause);
	}
	

}
