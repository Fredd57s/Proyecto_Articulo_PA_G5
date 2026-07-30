package com.proyecto.Resenas;

import com.proyecto.Resenas.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ResenasApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	// Ahora @MockBean funcionará perfectamente
	@MockBean
	private ReviewService reviewService;

	@Test
	void contextLoads() {
	}

	@Test
	void testDashboardRedirectWithoutAuth() {
		webTestClient.get().uri("/dashboard")
				.exchange()
				.expectStatus().is3xxRedirection();
	}

	@Test
	void testLoginViewLoadsCorrectly() {
		webTestClient.get().uri("/login")
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentTypeCompatibleWith("text/html");
	}
}