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

import com.csse.procurement.CSSE_WE_29.entity.Site;
import com.csse.procurement.CSSE_WE_29.service.SiteService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SiteController.class, secure = false)
public class SiteControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private SiteService siteService;
	
	private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
	private static final String TRUE = "true";

	//Site Name is required
	Site mockSite = new Site("5d8765f59c5c7e16b49403ca", "ST001", "Main Site");
	
	@Test
	//positive
	public void TestSiteAssigning() throws Exception {
		
		String sampleSite = "\"siteId\": \"ST001\",\"siteName\": \"Main Site\"";
		
		Mockito.when(siteService.save(Mockito.any(Site.class))).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("http://localhost:9200/api/site/insert-site")
				.accept(MediaType.APPLICATION_JSON).content(sampleSite)
					.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(CONTENT_TYPE, response.getContentType());
		assertEquals(TRUE, response.getContentAsString());
		
		
	}
	
	@Test
	public void TestGetSite() throws Exception {
		
		Mockito.when(siteService.findBySiteId(Mockito.anyString())).thenReturn(mockSite);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("http://localhost:9200/api/site/get-site/ST001")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		
		String expected = "{\"id\": \"5d8765f59c5c7e16b49403ca\",\"siteId\": \"ST001\",\"siteName\": \"Main Site\"}";
		
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		assertEquals(CONTENT_TYPE, result.getResponse().getContentType());
		
		System.out.println("Site content: " + result.getResponse().getContentAsString());
		
	}
	
	@Test
	public void TestUpdateSite() throws Exception {
		
		String sampleSite = "\"id\": \"5d875f08d25aeb48506d6b48\",\"site\": \"ST001\",\"siteName\": \"Building Site\"";
		
		Mockito.when(siteService.update(Mockito.any(Site.class))).thenReturn(true);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("http://localhost:9200/api/site/update-site")
				.accept(MediaType.APPLICATION_JSON).content(sampleSite)
					.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse responce = result.getResponse();
		
		System.out.println(responce.getContentType());
		
		assertEquals(HttpStatus.OK.value(), responce.getStatus());
		assertEquals(CONTENT_TYPE, responce.getContentType());
		assertEquals(TRUE, responce.getContentAsString());
		
		
	}
	
	//negative test cases
	@Test
	public void TestGetSiteForNegative() throws Exception {
		
		Mockito.when(siteService.findBySiteId(Mockito.anyString())).thenReturn(mockSite);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("http://localhost:9200/api/site/get-site/ST001")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		
		String expected = "{\"sample\": 120}";
		
		//negative condition
		JSONAssert.assertNotEquals(expected, result.getResponse().getContentAsString(), true);
		
		System.out.println("Site content: " + result.getResponse().getContentAsString());
		
	}
	
	

}
