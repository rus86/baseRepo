package com.ruscorporation.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import com.ruscorporation.exceptions.InvalidUserException;
import com.ruscorporation.model.Contact;
import com.ruscorporation.model.Greeting;
import com.ruscorporation.model.User;
import com.ruscorporation.service.UserManager;

public class UserManagerTest extends BaseTest {

	private static final String BASE_URI = "http://localhost:8080/api/getUser?userName=admin";
	private static final String GREETING_URI = "http://localhost:8080/api/greeting";
	private static final String CONTACT_LIST_URI = "http://localhost:8080/api/contact/list";
	private static final String CONTACT_GET_URI = "http://localhost:8080/api/contact/get";
	private static final String CONTACT_PUT_URI = "http://localhost:8080/api/contact/add";
	private static final String CONTACT_POST_URI = "http://localhost:8080/api/contact/create";
	private static final String CONTACT_DELETE_URI = "http://localhost:8080/api/contact/remove";

	// Sync rest template
	private RestTemplate restTemplate = new RestTemplate();

	// Async rest template
	private AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();

	@Autowired
	private UserManager userManager;

	private UserManager mockedUserManager;

	private User mockedUser;

	private List mockedUserList;

	@Mock
	private List secondMockedUserList;

	@Before
	public void init() {

		// Allow to use @Mock annotation
		MockitoAnnotations.initMocks(this);

		// Init
		mockedUserManager = mock(UserManager.class);
		mockedUser = new User("testUser", "testPassword");
		
		

		// Create stubs. Set up methods behavior.
		when(mockedUserManager.getUser("rusUser")).thenReturn(mockedUser);
		when(mockedUserManager.list()).thenThrow(
				new IndexOutOfBoundsException());

		//Init sync REST template		
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();

		// Add string message converter
		converters.add(new StringHttpMessageConverter());
		// Add JAXB(XML) message converter
		converters.add(new Jaxb2RootElementHttpMessageConverter());
		// Add JSON message converter
		converters.add(new MappingJackson2HttpMessageConverter());
		restTemplate.setMessageConverters(converters);
		
		//Init async REST template
		asyncRestTemplate.setMessageConverters(converters);

	}

	@Test
	public void getUserInJSON() {
		Greeting greeting = restTemplate.getForObject(GREETING_URI,
				Greeting.class);
		assertNotNull(greeting);
	}

	@Test
	public void getContactListInJSON() {
		List<Contact> contactList = restTemplate.getForObject(CONTACT_LIST_URI,
				ArrayList.class);
		assertNotNull(contactList);
	}

	@Test
	public void getContactByIdInJSON() {
		Contact contact = restTemplate.getForObject(CONTACT_GET_URI + "?id=1",
				Contact.class);
		assertNotNull(contact);
	}

	@Test
	public void addPUTContact() {
		Contact contact = new Contact();
		String name = "name" + System.currentTimeMillis();
		contact.setName(name);
		contact.setPhone("1234567");
		restTemplate.put(CONTACT_PUT_URI, contact);

		// Get contact
		Contact contactResponse = restTemplate.getForObject(CONTACT_GET_URI
				+ "?name=" + name, Contact.class);
		assertNotNull(contactResponse);
		assertEquals(name, contact.getName());

		// Remove contact
		restTemplate.delete(CONTACT_DELETE_URI + "/{id}",
				contactResponse.getId());
		Contact deletedContactResponse = restTemplate.getForObject(
				CONTACT_GET_URI + "?name=" + name, Contact.class);
		assertNull(deletedContactResponse);
	}

	/**
	 * Asynchronous get contact list
	 */
	@Test
	public void asyncGetContactListInJSON() {
		
		Future<ResponseEntity<ArrayList>> contactListFuture = asyncRestTemplate.getForEntity(CONTACT_LIST_URI,
				ArrayList.class);
		//waiting until method works...
		try {
			
			ResponseEntity<ArrayList> contactListResponse = contactListFuture.get();
			ArrayList contactList = contactListResponse.getBody();
			assertNotNull(contactList);
						
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addPOSTContact() {
		Contact contact = new Contact();
		String name = "name" + System.currentTimeMillis();
		contact.setName(name);
		contact.setPhone("1234567");
		restTemplate.postForObject(CONTACT_POST_URI, contact, Contact.class);
	}

	@Test
	public void testVerifyBehaviour() {
		mockedUserList = mock(List.class);
		// secondMockedUserList = mock(List.class);

		mockedUserList.add("First user");

		mockedUserList.add("Second user");
		mockedUserList.add("Second user");

		verify(mockedUserList).add("First user");

		// Verify exect number of invocation
		verify(mockedUserList, times(1)).add("First user");

		verify(mockedUserList, times(2)).add("Second user");

		verify(mockedUserList, never()).add("Third user");

		// Verify no interaction between lists(mocks)
		verifyZeroInteractions(mockedUserList, secondMockedUserList);

	}

	/**
	 * Test stub mock
	 */
	@Test
	public void testMockBehavior() {
		User emptyUser = mockedUserManager.getUser("someName");
		assertNull(emptyUser);

		User mockedUser = mockedUserManager.getUser("rusUser");
		assertEquals("testPassword", mockedUser.getPassword());

		// List<User> list = mockedUserManager.list();

	}

	@Test
	public void addUserTest() {
		User user = new User();
		String userName = "testUser";
		user.setUsername(userName);
		user.setPassword("123456");

		try {
			userManager.saveOrUpdate(user);
		} catch (InvalidUserException e) {
			e.printStackTrace();
		}

		// Get user by name
		User selectedUser = userManager.getUser(userName);
		assertNotNull(selectedUser);

	}
	
	

}
