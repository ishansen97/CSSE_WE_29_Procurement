package com.csse.procurement.CSSE_WE_29.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.csse.procurement.CSSE_WE_29.entity.Item;
import com.csse.procurement.CSSE_WE_29.entity.LoginCredentials;
import com.csse.procurement.CSSE_WE_29.service.ItemService;
import com.csse.procurement.CSSE_WE_29.service.LoginCredentialsService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = LoginCredentialsController.class, secure = false)
public class LoginCredentialsControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private LoginCredentialsService loginCredentialsService;
	
	private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
	private static final String TRUE = "true";
	
	LoginCredentials loginCredentials = new LoginCredentials("Ishan", "Ishan123", "Procurement");
	
	@Test
	public void TestLoginCredentailsCreation() throws Exception {
		String sampleLogin = "{\"username\": \"Sohan\",\"userType\": \"SiteManager\"}";
		Mockito.when(loginCredentialsService.saveLoginCredentials(Mockito.any(LoginCredentials.class), Mockito.anyString())).thenReturn(true);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("http://localhost:9200/api/login/insert-login")
				.accept(MediaType.APPLICATION_JSON).content(sampleLogin)
				.header("Authorization", "Basic=c29oYW4xMjM=")
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(CONTENT_TYPE, response.getContentType());
		assertEquals(TRUE, response.getContentAsString());
	}
	
	
	
	@Test
	public void TestLoginCredentailsUpdation() throws Exception {
		String sampleLogin = "{\"username\": \"Sohan\",\"userType\": \"SiteManager\"}";
		
		Mockito.when(loginCredentialsService.updateLoginCredentials(Mockito.any(LoginCredentials.class))).thenReturn(true);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("http://localhost:9200/api/login/update-credentials")
				.accept(MediaType.APPLICATION_JSON).content(sampleLogin)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(CONTENT_TYPE, response.getContentType());
		assertEquals(TRUE, response.getContentAsString());
	}
	
	@Test
	public void TestLoginCredentailsVerification() throws Exception {
		String sampleLogin = "{\"username\": \"Sohan\",\"userType\": \"SiteManager\"}";		
		Mockito.when(loginCredentialsService.isUserAvailable(Mockito.any(LoginCredentials.class))).thenReturn(true);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("http://localhost:9200/api/login/verify-user")
				.accept(MediaType.APPLICATION_JSON).content(sampleLogin)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(CONTENT_TYPE, response.getContentType());
		assertEquals(TRUE, response.getContentAsString());
	}
}
