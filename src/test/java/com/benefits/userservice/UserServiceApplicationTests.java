package com.benefits.userservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.TestPropertySources;



@SpringBootTest(properties = "spring.config.location=" +
		"classpath:/application.yaml" +
		",classpath:/application-*.yml"
)
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
