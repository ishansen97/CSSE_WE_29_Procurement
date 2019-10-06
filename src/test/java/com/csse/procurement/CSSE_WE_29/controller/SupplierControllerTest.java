package com.csse.procurement.CSSE_WE_29.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import com.csse.procurement.CSSE_WE_29.entity.Supplier;
import com.csse.procurement.CSSE_WE_29.service.ItemService;
import com.csse.procurement.CSSE_WE_29.service.SupplierService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SupplierController.class, secure = false)
public class SupplierControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private SupplierService supplierService;
	
	private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
	private static final String TRUE = "true";
	private List<Item> itemList = new ArrayList<>(
			Arrays.asList(
						new Item(110, 200)
					));
	
	Supplier supplier = new Supplier("5d70756c1238a32c84f75f39", "SP001", "Sumathi Constructions", "Malabe", itemList);
	
	@Test
	public void TestSupplierCreation() throws Exception {
		String sampleSupplier = "{\"supplierId\":\"SP001\",\"supplierName\":\"Sumathi Constructions\",\"supplierLocation\":\"Malabe\",\"itemList\":[{\"itemId\":110,\"itemPrice\":0.0,\"itemType\":null,\"itemQuantity\":200,\"itemMetric\":null,\"supplierId\":null,\"id\":null,\"critical\":false}],\"id\":\"5d70756c1238a32c84f75f39\"}";
		Mockito.when(supplierService.savePurchaseOrder(Mockito.any(Supplier.class))).thenReturn(true);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("http://localhost:9200/api/supplier/insert-supplier")
				.accept(MediaType.APPLICATION_JSON).content(sampleSupplier)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(CONTENT_TYPE, response.getContentType());
		assertEquals(TRUE, response.getContentAsString());
	}
	
	@Test
	public void TestSupplierRetrieval() throws Exception {
		
		Mockito.when(supplierService.findBySupplierId(Mockito.anyString())).thenReturn(supplier);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"http://localhost:9200/api/supplier/get-supplier/SP001").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		
		String sampleSupplier = "{\"supplierId\":\"SP001\",\"supplierName\":\"Sumathi Constructions\",\"supplierLocation\":\"Malabe\",\"itemList\":[{\"itemId\":110,\"itemPrice\":0.0,\"itemType\":null,\"itemQuantity\":200,\"itemMetric\":null,\"supplierId\":null,\"id\":null,\"critical\":false}],\"id\":\"5d70756c1238a32c84f75f39\"}";
		
		JSONAssert.assertEquals(sampleSupplier, result.getResponse().getContentAsString(), true);
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		assertEquals(CONTENT_TYPE, result.getResponse().getContentType());
		
		System.out.println("Very good");
	}
	
	
}
