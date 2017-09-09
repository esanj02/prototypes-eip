package prototype.eip.camel.eip;

import static org.apache.camel.LoggingLevel.DEBUG;
import static prototype.eip.camel.Constants.CACHE_ACTION;
import static prototype.eip.camel.Constants.CACHE_ACTION_PUT_NEW;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


/**
 * 
 * @author jide
 *
 */
@Component("errorRouteBuilder")
public class ErrorRouteBuilder extends RouteBuilder {

	private static final Logger logger = LoggerFactory.getLogger(ErrorRouteBuilder.class);

	private final String ERROR_routeId;
	private final String ERROR_endpointUri;
	
	@Autowired
	private final Environment env;
	
	
	@Autowired
	public ErrorRouteBuilder(final Environment env) {
		super();
		
		this.env = env;
		this.ERROR_routeId = this.env.getProperty("camel.eip.error.endpoint.routeid");
		this.ERROR_endpointUri = this.env.getProperty("camel.eip.error.endpoint.uri");
	}

	
	/**
	 * {@inheritDoc}
	 * @see org.apache.camel.builder.RouteBuilder#configure()
	 */
	public void configure() throws Exception {
		logger.info("starting route processing...");
		
		from(ERROR_endpointUri)
			.routeId(ERROR_routeId)
			.startupOrder(0)
				.log(DEBUG, "Error route event processing: STARTED")
					.setHeader(CACHE_ACTION, constant(CACHE_ACTION_PUT_NEW))
					
				.log(DEBUG, "Error route event processing: FINISHED")
			.stop()
			.end();
		
	}
	

}
