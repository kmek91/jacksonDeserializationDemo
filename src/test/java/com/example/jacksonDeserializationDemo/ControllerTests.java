package com.example.jacksonDeserializationDemo;

import com.example.jacksonDeserializationDemo.dto.Pet;
import com.example.jacksonDeserializationDemo.dto.PetWithIgnoredFields;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ControllerTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void normalDeserialization() {
		Pet fido = new Pet(5, "Fido", "blue");
		String response = restTemplate.postForObject("http://localhost:" + port + "/pet", fido, String.class);
		assertThat(response, equalTo("{\"age\":5,\"name\":\"Fido\",\"favoriteColor\":\"blue\"}"));
	}

	@Test
	public void ignoredDeserialization() {
		PetWithIgnoredFields fifi = new PetWithIgnoredFields(7, "Fifi", "green");
		String response = restTemplate.postForObject("http://localhost:" + port + "/petIgnored", fifi, String.class);
		assertThat(response, equalTo("{\"age\":0,\"name\":\"Fifi\",\"favoriteColor\":null}"));
	}

}