package com.sube.plus.apaseo.sube_back;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(properties = {"spring.data.mongodb.uri=mongodb://localhost:27017/testdb"})
@ActiveProfiles("test")
class SubeBackApplicationTests {

	@Test
	void contextLoads() {
	}

}
