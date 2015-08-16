package com.ruscorporation.tests;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruscorporation.model.Contact;
import com.ruscorporation.service.ContactService;

public class ContactTests extends BaseTests {

	@Autowired
	private ContactService contactService;

	// If we use RestTemplate you need to start server. RestTemplate use for
	// client
	// side service call.
	// If we use MockMvc you don't need to start web server.
	private MockMvc mockMvc;

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Before
	public void onSetup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
		try {
			contactCreate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void getContactList() throws Exception {
		mockMvc.perform(get("/api/contact/list").accept(APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$", notNullValue()));

	}

	@Test
	public void getContactByLicenseId() throws Exception {
		mockMvc.perform(
				get("/api/contact/getByLicense/{licenseId}", "999").accept(
						APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.firstName", is("Ivan")))
				.andExpect(jsonPath("$.lastName", is("Petrov")));

	}

	// @Test
	public void contactCreate() throws Exception {
		Contact contact = new Contact();
		contact.setFirstName("Ivan");
		contact.setLastName("Petrov");
		contact.setAge((short) 25);
		contact.setDriverLicense("999");
		mockMvc.perform(
				post("/api/contact/create").contentType(APPLICATION_JSON_UTF8)
						.content(convertObjectToByte(contact))
						.accept(APPLICATION_JSON_UTF8)).andExpect(
				status().isOk());
	}

	private byte[] convertObjectToByte(Object object)
			throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		// mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsBytes(object);
	}

}
