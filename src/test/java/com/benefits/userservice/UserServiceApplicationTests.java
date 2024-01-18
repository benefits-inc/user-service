package com.benefits.userservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest(properties = "spring.config.location=" +
		"classpath:/application.yaml" +
		",classpath:/application-actuator.yml" +
		",classpath:/application-config.yml" +
		",classpath:/application-eureka.yml" +
		",classpath:/application-rabbitmq.yml" +
		",classpath:/application-swagger.yml" +
		",classpath:/application-zipkin.yml"
)
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
