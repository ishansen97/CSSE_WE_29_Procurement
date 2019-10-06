package com.csse.procurement.CSSE_WE_29.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import com.csse.procurement.CSSE_WE_29.entity.PurchaseOrder;
import com.csse.procurement.CSSE_WE_29.service.ItemService;
import com.csse.procurement.CSSE_WE_29.service.PurchaseOrderService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PurchaseOrderController.class, secure = false)
public class PurchaseOrderControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PurchaseOrderService purchaseOrderService;
	
	private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
	private static final String TRUE = "true";
	private List<Item> itemList = new ArrayList<>(
			Arrays.asList(
						new Item(110, 200),
						new Item(111, 300)
					));
	
	PurchaseOrder purchaseOrder = new PurchaseOrder("5d70d1661238a356bca42fad", "P0000001", "Construction", "Pending", "Yahoo", "No. 2000, Colombo", new Date(), new Date(), itemList, 0, 1000000.0, "SP002", "ST001");
	
	@Test
	public void TestPurchaseOrderCreation() throws Exception {
		String samplePurchaseOrder = "{\"ordType\": \"Construction\",\"ordStatus\": \"Pending\",\"companyName\": \"Rathnasiri\",\"deliveryAddress\": \"No.200, Gampaha\",\"ordDate\": \"2019-09-12\",\"deliveryDate\": \"2019-09-19\",\"itemIdList\": [{\"itemId\": 100,\"itemQuantity\": 20},{\"itemId\": 103,\"itemQuantity\": 40}],\"cost\": 55000,\"supplierId\": \"SP002\"}";
		Mockito.when(purchaseOrderService.savePurchaseOrder(Mockito.any(PurchaseOrder.class))).thenReturn(true);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("http://localhost:9200/api/purchase/insert-purchase-order")
				.accept(MediaType.APPLICATION_JSON).content(samplePurchaseOrder)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(CONTENT_TYPE, response.getContentType());
		assertEquals(TRUE, response.getContentAsString());
	}
	
	
	
	@Test
	public void TestPurchaseOrderUpdation() throws Exception {
		String samplePurchaseOrder = "{\"_id\": \"5d70d1661238a356bca42fad\",\"ordId\": \"P0000001\",\"ordType\": \"Construction\",\"ordStatus\": \"Pending\",\"companyName\": \"Rathnasiri\",\"deliveryAddress\": \"No.200, Gampaha\",\"ordDate\": \"2019-09-12\",\"deliveryDate\": \"2019-09-19\",\"itemIdList\": [\"itemId\": 100,\"itemQuantity\": 20},{\"itemId\": 103,\"itemQuantity\": 40}],\"cost\": 55000,\"supplierId\": \"SP002\"}";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("http://localhost:9200/api/purchase/update-purchase-order")
				.accept(MediaType.APPLICATION_JSON).content(samplePurchaseOrder)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(CONTENT_TYPE, response.getContentType());
		assertEquals(TRUE, response.getContentAsString());
	}
	
	//negative test cases
	@Test
	public void TestPurchaseOrderRetrieval() throws Exception {
		
		Mockito.when(purchaseOrderService.findByOrderId(Mockito.anyString())).thenReturn(purchaseOrder);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"http://localhost:9200/api/purchase/get-purchase-order/P0000001").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String expected = "{\"_id\": \"5d70d1661238a356bca42fad\",\"ordId\": \"P0000001\",\"ordType\": \"Construction\",\"ordStatus\": \"Pending\",\"companyName\": \"Rathnasiri\",\"deliveryAddress\": \"No.200, Gampaha\",\"ordDate\": \"2019-09-12\",\"deliveryDate\": \"2019-09-19\",\"itemIdList\": [\"itemId\": 100,\"itemQuantity\": 20},{\"itemId\": 103,\"itemQuantity\": 40}],\"cost\": 55000,\"supplierId\": \"SP002\"}";
		
		JSONAssert.assertNotEquals(expected, result.getResponse().getContentAsString(), true);
		
		System.out.println("Very good");
	}
}
