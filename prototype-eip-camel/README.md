# prototypes-eip-camel
Prototype project - to show case event error handling strategy based on the apache camel EIP framework implementation


### References
* [Apache Camel](camel.apache.org)
* [Spring Boot](https://projects.spring.io/spring-boot/)
	
### Tools
| Dependency | Version |
| ------ | ------ |
| Java | 1.8 |
| Maven | 3.0.x > higher |
| Apache Camel | 2.18.2 > higher |
| Spring boot | 1.5.6.RELEASE > higher |


### Maven

#### Create project
```sh
$ mvn archetype:generate \
		-DgroupId=prototype.eip.camel \
		-DartifactId=prototype-eip-camel \
		-DarchetypeArtifactId=maven-archetype-quickstart \
		-DinteractiveMode=false
```