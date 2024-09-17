package me.sukumdev.bookapi;

import me.sukumdev.bookapi.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
class BookapiApplicationTests {

	@Autowired
	private UserController controller;

	@Test
	void contextLoads() {
		assertNotNull(controller);
	}

}
