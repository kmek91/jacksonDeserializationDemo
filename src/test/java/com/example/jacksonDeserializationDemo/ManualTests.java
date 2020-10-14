package com.example.jacksonDeserializationDemo;

import com.example.jacksonDeserializationDemo.dto.Pet;
import com.example.jacksonDeserializationDemo.dto.PetWithIgnoredFields;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

@SpringBootTest
class ManualTests {

	private final ObjectMapper mapper = new ObjectMapper();

	@Test
	void normalSerialization() throws JsonProcessingException {
		Pet fido = new Pet(5, "Fido", "blue");

		String petAsJsonString = mapper.writeValueAsString(fido);
		assertThat(petAsJsonString, equalTo("{\"age\":5,\"name\":\"Fido\",\"favoriteColor\":\"blue\"}"));
	}

	@Test
	void normalDeserialization() throws JsonProcessingException {
		String petAsJsonString = "{\"age\":5,\"name\":\"Fido\",\"favoriteColor\":\"blue\"}";

		Pet fido = mapper.readValue(petAsJsonString, Pet.class);

		assertThat(fido.getAge(), equalTo(5));
		assertThat(fido.getName(), equalTo("Fido"));
		assertThat(fido.getFavoriteColor(), equalTo("blue"));
	}

	@Test
	void serializationWithIgnoredFieldsStillBeingSerialized() throws JsonProcessingException {
		PetWithIgnoredFields fifi = new PetWithIgnoredFields(7, "Fifi", "green");

		String petAsJsonString = mapper.writeValueAsString(fifi);
		assertThat(petAsJsonString, equalTo("{\"age\":7,\"name\":\"Fifi\",\"favoriteColor\":\"green\"}"));
	}

	@Test
	void deserializationWithIgnoredFieldsBeingIgnored() throws JsonProcessingException {
		String petAsJsonString = "{\"age\":7,\"name\":\"Fifi\",\"favoriteColor\":\"green\"}";

		PetWithIgnoredFields fifi = mapper.readValue(petAsJsonString, PetWithIgnoredFields.class);

		assertThat(fifi.getName(), equalTo("Fifi"));
		assertThat(fifi.getAge(), equalTo(0)); // the field is an int, the default value is 0
		assertThat(fifi.getFavoriteColor(), nullValue()); // the field is set to null
	}

}
