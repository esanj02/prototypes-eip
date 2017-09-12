package prototype.eip.mule.core;

import static java.lang.System.err;
import static java.lang.System.out;

import java.util.Collection;
import java.util.Iterator;

import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.api.construct.FlowConstruct;
import org.mule.api.context.notification.MuleContextNotificationListener;
import org.mule.api.context.notification.ServerNotification;
import org.mule.context.notification.MuleContextNotification;
import org.mule.context.notification.NotificationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * @author Jide Esan
 *
 */
@SuppressWarnings("rawtypes")
public class FlowOrderStartupListener extends AbstractStartupListener implements MuleContextNotificationListener {

	private static final Logger logger = LoggerFactory.getLogger(FlowOrderStartupListener.class);

	
	public FlowOrderStartupListener(MuleContext context) throws NotificationException {
		super(context);
		this.context.registerListener(this);
	}
	
	
	public void onNotification(ServerNotification notification) {
		logger.info("got a server notification...");
		
		if (notification.getAction() == MuleContextNotification.CONTEXT_STARTED) {
			out.println("Starting stopped flows...");
			Collection<FlowConstruct> flowConstructCollection = context.getRegistry().lookupFlowConstructs();
			Iterator<FlowConstruct> iterator = flowConstructCollection.iterator();
			
			while (iterator.hasNext()) {
				FlowConstruct flowConstruct = iterator.next();
				if (flowConstruct.getLifecycleState().isStopped()) {
					out.println("Starting " + flowConstruct.getName() + " flowConstruct...");		
					
					try {				
						
						this.context.getRegistry().registerFlowConstruct(flowConstruct);
									
						if(flowConstruct.getLifecycleState().isStopped())
							this.context
								.getRegistry()
								.lookupFlowConstruct(flowConstruct.getName())
								.getMuleContext()
								.getLifecycleManager()
								.fireLifecycle("start");
						
					} catch (MuleException muleException) {
						err.println(muleException.getDetailedMessage());
					}
				}
			}
			
			out.println("Stopped flows have been started...");
			
		}
	}

}
