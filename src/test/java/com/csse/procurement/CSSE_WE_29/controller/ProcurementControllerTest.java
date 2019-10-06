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
import com.csse.procurement.CSSE_WE_29.entity.ProcurementStaff;
import com.csse.procurement.CSSE_WE_29.service.ItemService;
import com.csse.procurement.CSSE_WE_29.service.ProcurementStaffService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProcurementController.class, secure = false)
public class ProcurementControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProcurementStaffService procurementStaffService;
	
	private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
	private static final String TRUE = "true";
	
	ProcurementStaff mockProcurement = new ProcurementStaff("S005", "Proc2", "Proc_003", "Proc125");
	
	@Test
	public void TestProcurementStaffCreation() throws Exception {
		String sampleProcurement = "{\"staffId\": \"S006\", \"staffName\": \"Proc2\", \"staffUserName\": \"Proc_003\", \"staffPassword\": \"Proc125\"}";
		Mockito.when(procurementStaffService.saveProcurementStaff(Mockito.any(ProcurementStaff.class))).thenReturn(true);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("http://localhost:9200/api/procurement/insert-procurement")
				.accept(MediaType.APPLICATION_JSON).content(sampleProcurement)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(CONTENT_TYPE, response.getContentType());
		assertEquals(TRUE, response.getContentAsString());
	}
	
	@Test
	public void TestProcurementStaffRetrieval() throws Exception {
		
		Mockito.when(procurementStaffService.getById(Mockito.anyString())).thenReturn(mockProcurement);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"http://localhost:9200/api/procurement/get-procurement/S005").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		
		String expected = "{\"staffId\": \"S005\", \"staffName\": \"Proc2\", \"staffUserName\": \"Proc_003\", \"staffPassword\": \"Proc125\"}";
		
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		assertEquals(CONTENT_TYPE, result.getResponse().getContentType());
		
		System.out.println("Very good");
	}

	
	@Test
	public void TestProcurementStaffUpdation() throws Exception {
		String sampleProcurement = "{\"staffId\": \"S006\", \"staffName\": \"TestProc2\", \"staffUserName\": \"TestProc_003\", \"staffPassword\": \"Proc125\"}";
		Mockito.when(procurementStaffService.updateProcurementStaff(Mockito.any(ProcurementStaff.class))).thenReturn(true);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("http://localhost:9200/api/procurement/update-procurement")
				.accept(MediaType.APPLICATION_JSON).content(sampleProcurement)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(CONTENT_TYPE, result.getResponse().getContentType());
		assertEquals(TRUE, result.getResponse().getContentAsString());
	}
	
	public void TestProcurementStaffDeletion() throws Exception {
		String sampleProcurement = "{\"staffId\": \"S006\", \"staffName\": \"TestProc2\", \"staffUserName\": \"TestProc_003\", \"staffPassword\": \"Proc125\"}";
		Mockito.when(procurementStaffService.deleteProcurementStaff(Mockito.any(ProcurementStaff.class))).thenReturn(true);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("http://localhost:9200/api/procurement/delete-procurement")
				.accept(MediaType.APPLICATION_JSON).content(sampleProcurement)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(CONTENT_TYPE, result.getResponse().getContentType());
		assertEquals(TRUE, result.getResponse().getContentAsString());
	}
	
}
