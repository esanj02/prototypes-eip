package prototype.eip.mule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


/**
 * 
 * @author Jide Esan
 *
 */
@SpringBootApplication
@ComponentScan(
		basePackages = { 
			"prototype.eip.mule" 
		}
	)
@EnableAutoConfiguration(
		exclude = {
			CassandraAutoConfiguration.class, CassandraDataAutoConfiguration.class, 
			DataSourceAutoConfiguration.class, ElasticsearchDataAutoConfiguration.class,
			ElasticsearchAutoConfiguration.class
		}
	)
public class Bootstrap {
	
	private static final Logger logger = LoggerFactory.getLogger(Bootstrap.class);

    public static void main(String[] args) {
    	logger.info("starting...");
		SpringApplication.run(Bootstrap.class, args);
	
    }
        
}
