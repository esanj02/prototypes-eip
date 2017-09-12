package prototype.eip.mule.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


/**
 * 
 * @author Jide Esan
 *
 */
@Configuration
public class SpringParentConfiguration {
	
	private ApplicationContext applicationContext;

	
	@Bean(name="parentConfigContext")
	public ApplicationContext parentConfigContext(){
		AnnotationConfigApplicationContext parentConfigContext = null;
		parentConfigContext = new AnnotationConfigApplicationContext(
				/*GatewayServiceConfiguration.class*/);
		
		applicationContext = parentConfigContext;
		
		return parentConfigContext;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	
}
