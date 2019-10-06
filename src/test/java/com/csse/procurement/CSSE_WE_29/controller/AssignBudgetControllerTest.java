package com.csse.procurement.CSSE_WE_29.controller;

import static org.junit.Assert.*;

import javax.print.attribute.standard.Media;

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

import com.csse.procurement.CSSE_WE_29.entity.AssignBudget;
import com.csse.procurement.CSSE_WE_29.service.AssignBudgetService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AssignBudgetController.class, secure = false)
public class AssignBudgetControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AssignBudgetService assignBudgetService;
	
	private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
	private static final String TRUE = "true";

	AssignBudget mockAssignBudget = new AssignBudget("5d8765f59c5c7e16b49403ca", 1, "ST001", 1000000);
	
	@Test
	public void TestBudgetAssigning() throws Exception {
//		Mockito.when(assignBudgetService.save(Mockito.any(AssignBudget.class))
//		AssignBudget mockAssignBudget = new AssignBudget(2, "ST002", 1200000);
		String sampleAssignBudget = "{\"siteId\": \"ST001\", \"siteBudget\": 1000000 }";
		
				
	Mockito.when(assignBudgetService.save(Mockito.any(AssignBudget.class))).thenReturn(true);
			RequestBuilder requestBuilder = MockMvcRequestBuilders
					.post("http://localhost:9200/api/budget/insert-budget")
					.accept(MediaType.APPLICATION_JSON).content(sampleAssignBudget)
									.contentType(MediaType.APPLICATION_JSON);
			
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			
			MockHttpServletResponse response = result.getResponse();
			
			assertEquals(HttpStatus.OK.value(), response.getStatus());
			assertEquals(CONTENT_TYPE, response.getContentType());
			assertEquals(TRUE, response.getContentAsString());
			
	}
	
	@Test
	public void TestGetBudget() throws Exception {
		
		Mockito.when(assignBudgetService.findByBudgetId(Mockito.anyInt())).thenReturn(mockAssignBudget);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("http://localhost:9200/api/budget/get-budget/2")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
				System.out.println(result.getResponse());
				
				String expected = "{\"_id\": \"5d8765f59c5c7e16b49403ca\",\"budgetId\": 1,\"siteId\": \"ST001\",\"siteBudget\": 1000000.0}";
				
				JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
				assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
				assertEquals(CONTENT_TYPE, result.getResponse().getContentType());
				
				System.out.println("Content: " + result.getResponse().getContentAsString());
	}
	
	@Test
	public void TestUpdateBudget() throws Exception {
		String sampleBudget = "{\"id\": \"5d8b3b801238a34e3c35a5df\", \"budgetId\": 2, \"siteId\": \"ST002\", \"siteBudget\": 1200000 }";
			
		Mockito.when(assignBudgetService.update(Mockito.any(AssignBudget.class))).thenReturn(true);

			RequestBuilder requestBuilder = MockMvcRequestBuilders
					.put("http://localhost:9200/api/budget/update-budget")
					.accept(MediaType.APPLICATION_JSON).content(sampleBudget).contentType(MediaType.APPLICATION_JSON);
			
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			
			MockHttpServletResponse response = result.getResponse();
			
			assertEquals(HttpStatus.OK.value(), response.getStatus());
			assertEquals(CONTENT_TYPE, response.getContentType());
			assertEquals(TRUE, response.getContentAsString());
			
	}
	
	
	//negative test cases
	@Test
	public void TestGetBudgetForNegative() throws Exception {
		
		Mockito.when(assignBudgetService.findByBudgetId(Mockito.anyInt())).thenReturn(mockAssignBudget);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("http://localhost:9200/api/budget/get-budget/2")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
				System.out.println(result.getResponse());
				
				String expected = "{\"sample\": 120}";
				
				//testing for negative response
				JSONAssert.assertNotEquals(expected, result.getResponse().getContentAsString(), true);
				
				System.out.println("Content: " + result.getResponse().getContentAsString());
	}
	
	
}
