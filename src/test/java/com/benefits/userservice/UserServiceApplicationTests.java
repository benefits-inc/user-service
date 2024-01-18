package com.benefits.userservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.TestPropertySources;


@TestPropertySource(properties = {"spring.config.location = classpath:/application.yaml",
		"spring.config.location = classpath:/application-actuator.yml",
		"spring.config.location = classpath:/application-config.yml"})
@SpringBootTest(properties = "spring.profiles.active:prod")
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
