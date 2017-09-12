package prototype.eip.mule.core;

import org.mule.api.MuleContext;
import org.mule.context.notification.NotificationException;


/**
 * 
 * @author Jide Esan
 *
 */
public abstract class AbstractStartupListener {

	protected MuleContext context = null;
	
	
	public AbstractStartupListener(){}
	
	public AbstractStartupListener(MuleContext context) throws NotificationException {
		this.context = context;
	}
	
}
