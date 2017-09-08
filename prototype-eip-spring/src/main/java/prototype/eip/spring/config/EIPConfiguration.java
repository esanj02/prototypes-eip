package prototype.eip.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.ImportResource;


/**
 * 
 * @author Jide Esan
 *
 */
@ImportResource({
	"classpath:META-INF/spring/spring-config-eip-common.xml",
	"classpath:META-INF/spring/spring-config-eip-error.xml"
})
@Configuration("eIPConfiguration")
public class EIPConfiguration {

}
