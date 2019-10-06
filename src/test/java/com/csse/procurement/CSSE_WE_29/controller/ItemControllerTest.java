package com.csse.procurement.CSSE_WE_29.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.UseParametersRunnerFactory;
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
import com.csse.procurement.CSSE_WE_29.service.ItemService;
import com.csse.procurement.CSSE_WE_29.service.SupplierService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ItemController.class, secure = false)
public class ItemControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ItemService itemService;
	
	@MockBean
	private SupplierService supplierService;
	
	private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
	private static final String TRUE = "true";
	
	Item mockItem = new Item("5d7085ca1238a322e87d639f", 100, 1000.0, "Cement", 100, "Bags", "SP001", false);
	
	@Test
	public void TestItemCreation() throws Exception {
//		Item mockItem = new Item(2000.0, "Rocks", 100, "Pieces", "SP002", true);
		String sampleItem = "{\"itemPrice\": 2000.0, \"itemType\": \"Rocks\", \"itemQuantity\": 300, \"itemMetric\": \"Pieces\", \"supplierId\": \"SP002\", \"critical\": false }";
		Mockito.when(itemService.saveItem(Mockito.any(Item.class))).thenReturn(true);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("http://localhost:9200/api/item/insert-item")
				.accept(MediaType.APPLICATION_JSON).content(sampleItem)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		assertEquals(CONTENT_TYPE, response.getContentType());
		assertEquals(TRUE, response.getContentAsString());
	}
	
	@Test
	public void TestItemRetrieval() throws Exception {
		
		Mockito.when(itemService.findByItemId(Mockito.anyInt())).thenReturn(mockItem);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"http://localhost:9200/api/item/get-item/100").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		
		String expected = "{\"itemId\": 100, \"itemPrice\": 1000.0, \"itemType\": \"Cement\", \"itemQuantity\": 100, \"itemMetric\": \"Bags\", \"supplierId\": \"SP001\", \"id\": \"5d7085ca1238a322e87d639f\", \"critical\": false }";
		
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		assertEquals(CONTENT_TYPE, result.getResponse().getContentType());
		
		System.out.println("Very good");
	}
	
	@Test
	public void TestItemUpdation() throws Exception {
		String sampleItem = "{\"itemPrice\": 2000.0, \"itemType\": \"Rocks\", \"itemQuantity\": 300, \"itemMetric\": \"Pieces\", \"supplierId\": \"SP002\", \"critical\": false }";
		Mockito.when(itemService.updateItem(Mockito.any(Item.class))).thenReturn(true);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("http://localhost:9200/api/item/update-item")
				.accept(MediaType.APPLICATION_JSON).content(sampleItem)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(CONTENT_TYPE, response.getContentType());
		assertEquals(TRUE, response.getContentAsString());
	}
	
	@Test
	public void TestItemDeletion() throws Exception {
		String sampleItem = "{\"itemId\": 104, \"itemPrice\": 1000.0, \"itemType\": \"Cement\", \"itemQuantity\": 100, \"itemMetric\": \"Bags\", \"supplierId\": \"SP001\", \"id\": \"5d7085ca1238a322e87d639f\", \"critical\": false }";
		Mockito.when(itemService.deleteItem(Mockito.any(Item.class))).thenReturn(true);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("http://localhost:9200/api/item/delete-item")
				.accept(MediaType.APPLICATION_JSON).content(sampleItem)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
}
