package prototype.eip.mule.core;


import static java.lang.System.err;
import static java.lang.System.out;

import java.util.Collection;
import java.util.Iterator;

import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.api.context.notification.MuleContextNotificationListener;
import org.mule.api.context.notification.ServerNotification;
import org.mule.api.service.Service;
import org.mule.context.notification.MuleContextNotification;
import org.mule.context.notification.NotificationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Jide Esan
 *
 */
//@Component("serviceOrderStartup")
@SuppressWarnings({ "rawtypes", "deprecation" })
public class ServiceOrderStartupListener extends AbstractStartupListener implements MuleContextNotificationListener {

	private static final Logger logger = LoggerFactory.getLogger(ServiceOrderStartupListener.class);

		
	public ServiceOrderStartupListener(MuleContext context) throws NotificationException {
		super(context);
		this.context.registerListener(this);
	}

	public void onNotification(ServerNotification notification) {
		logger.info("got a server notification...");
		
		if (notification.getAction() == MuleContextNotification.CONTEXT_STARTED) {
			out.println("Resuming paused services...");
			Collection<Service> serviceCollection = context.getRegistry().lookupServices();
			Iterator<Service> iterator = serviceCollection.iterator();
			
			while (iterator.hasNext()) {
				Service service = iterator.next();
				if (service.isPaused()) {
					out.println("Resuming " + service.getName() + " service...");		
					
					try {				
						
						service.resume();	
						
					} catch (MuleException muleException) {
						err.println(muleException.getDetailedMessage());
					}
				}
			}
			out.println("Paused services have been resumed...");
		}
	}

}
