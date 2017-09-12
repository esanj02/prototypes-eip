package prototype.eip.mule.config;

import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.api.config.ConfigurationException;
import org.mule.config.DefaultMuleConfiguration;
import org.mule.config.spring.SpringXmlConfigurationBuilder;
import org.mule.context.DefaultMuleContextBuilder;
import org.mule.context.DefaultMuleContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.util.Assert;

import prototype.eip.mule.core.FlowOrderStartupListener;

/**
 * 
 * @author Jide Esan
 *
 */
@Configuration
public class ESBConfiguration {

	private final static String MULE_FLOWS_CONFIG = "META-INF/mule/mule-flows-config.xml";
	
	@Autowired
	private SpringParentConfiguration springConfigContext;
	
	
	private MuleContext muleContext;
	
	
	@Bean(name="muleConfigContext")
	public String[] muleConfigContext(){
		return new String[]{
			/*MULE_FLOWS_ASYNC_CONFIG,*/
			MULE_FLOWS_CONFIG};
	}
	
	@Bean(name="muleConfigurationBuilder")
	public SpringXmlConfigurationBuilder muleConfigurationBuilder() throws ConfigurationException {
		SpringXmlConfigurationBuilder springConfigBuilder = null;
		springConfigBuilder = new SpringXmlConfigurationBuilder(muleConfigContext());
		springConfigBuilder.setUseDefaultConfigResource(true);
		//springConfigBuilder.setParentContext(springConfigContext.getApplicationContext());
		
		return springConfigBuilder;
	}
	
	@Bean(name="muleConfiguration")
	@DependsOn("muleConfigurationBuilder")
	public DefaultMuleConfiguration muleConfiguration(){
		DefaultMuleConfiguration muleConfiguration = null;
		muleConfiguration = new DefaultMuleConfiguration();
		muleConfiguration.setId("");
		muleConfiguration.setContainerMode(true);
		
		return muleConfiguration;
	}
	
	@Bean(name="muleContextBuilder")
	@DependsOn("muleConfiguration")
	public DefaultMuleContextBuilder muleContextBuilder(){
		DefaultMuleContextBuilder muleContextBuilder = null;
		muleContextBuilder = new DefaultMuleContextBuilder();
		muleContextBuilder.setMuleConfiguration(muleConfiguration());
		
		return muleContextBuilder;
	}
	
	@Bean(name="muleContextFactory")
	@DependsOn("muleContextBuilder")
	public DefaultMuleContextFactory muleContextFactory(){
		DefaultMuleContextFactory muleContextFactory = null;
	
		try {
			
			muleContextFactory = new DefaultMuleContextFactory();
			muleContext = muleContextFactory.createMuleContext(
					muleConfigurationBuilder(), muleContextBuilder());
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return muleContextFactory;
	}
	
	@Bean(name="startMuleContext")
	@DependsOn("muleContextFactory")
	public Object startMuleContext(){
		
		Assert.notNull(muleContext);
		
		if (!muleContext.isStarted()) {
			try {
				
				//new ServiceOrderStartupListener(muleContext);
				new FlowOrderStartupListener(muleContext);
				
				muleContext.start();
				
			} catch (MuleException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return muleContext.getStartDate();
	}
	
	/*@Bean
	public Object shutDownMuleContext(){
		
		assert muleContext != null;
		if (!muleContext.isStarted()) {
			try {
				
				muleContext.stop();
				
			} catch (InitialisationException | ConfigurationException e) {
				e.printStackTrace();
			} catch (MuleException e) {
				e.printStackTrace();
			}
		}
		
		return null;
		
	}*/
	
}
