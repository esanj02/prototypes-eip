# prototypes-eip-camel
Prototype project - to show case event error handling strategy based on the apache camel EIP framework implementation


### References
	*[Spring Boot] (https://projects.spring.io/spring-boot/)
	*[Spring Integration] (https://projects.spring.io/spring-integration/)
	
### Pre-requisites
| Dependency | Version |
| ------ | ------ |
| Java | 1.8 |
| Maven | 3.0.x > higher |
| Spring boot | 1.5.6.RELEASE > higher |
| Apache Camel | 2.18.2 > higher |


### Maven

#### Create project
```sh
$ mvn archetype:generate -DgroupId=prototype.eip.camel -DartifactId=prototype-eip-camel -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```