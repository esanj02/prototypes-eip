package prototype.eip.camel.config;

import java.util.Arrays;
import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.ShutdownRoute;
import org.apache.camel.ShutdownRunningTask;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.processor.interceptor.Tracer;
import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import prototype.eip.camel.eip.ErrorRouteBuilder;


/**
 * 
 * @author Jide Esan
 *
 */
public class EipJavaConfiguration extends CamelConfiguration {

	@Autowired
	protected Environment env;
	
	@Autowired
    private ErrorRouteBuilder errorRouteBuilder;
	

	/**
	 * {@inheritDoc}
	 * @see org.apache.camel.spring.javaconfig.CamelConfiguration#setupCamelContext(org.apache.camel.CamelContext)
	 */
    protected void setupCamelContext(CamelContext camelContext) throws Exception {
        camelContext.addComponent("properties", propertiesComponent());
        camelContext.setTracing(Boolean.TRUE);
        camelContext.setAllowUseOriginalMessage(Boolean.TRUE);
        camelContext.setStreamCaching(Boolean.TRUE);
        camelContext.setShutdownRoute(ShutdownRoute.Default);
        camelContext.setShutdownRunningTask(ShutdownRunningTask.CompleteAllTasks);
        //camelContext.setShutdownStrategy(ShutdownStrategy);
        camelContext.setUseMDCLogging(Boolean.TRUE);
                
        super.setupCamelContext(camelContext);
    }
 
    @Bean(name = "camelTracer")
    public Tracer camelTracer() {
        final Tracer tracer = new Tracer();
        tracer.setTraceExceptions(true);
        tracer.setTraceInterceptors(true);
        tracer.setLogName("");
        
        return tracer;
    }
    
    /**
     * {@inheritDoc}
     * @see org.apache.camel.spring.javaconfig.CamelConfiguration#routes()
     */
    public List<RouteBuilder> routes() {
        return Arrays.asList(
        		errorRouteBuilder);
    }


	/**
	 * {@inheritDoc}
	 * @see com.portxchange.gateway.config.EipJavaConfiguration#propertiesComponent()
	 */
    @Bean
	public PropertiesComponent propertiesComponent(){
		final PropertiesComponent propertiesComponent = new PropertiesComponent();
		propertiesComponent.setLocation("classpath:application.properties");
		
		return propertiesComponent;
	}
   
	
}
