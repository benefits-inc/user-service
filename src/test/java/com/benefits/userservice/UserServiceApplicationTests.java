package com.benefits.userservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.TestPropertySources;


@TestPropertySource(properties = {"spring.config.location = classpath:application*.yml", "spring.config.location = classpath:application*.yaml"})
@SpringBootTest(properties = "spring.profiles.active:prod")
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
