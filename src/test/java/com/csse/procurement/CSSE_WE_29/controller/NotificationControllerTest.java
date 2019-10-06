package com.csse.procurement.CSSE_WE_29.controller;

import static org.junit.Assert.assertEquals;

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

import com.csse.procurement.CSSE_WE_29.entity.Notification;
import com.csse.procurement.CSSE_WE_29.service.NotificationService;
import com.csse.procurement.CSSE_WE_29.service.NotificationSubjectImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(value = NotificationController.class, secure = false)
public class NotificationControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private NotificationService notificationService;
	@MockBean
	private NotificationSubjectImpl notificationSubject;
	
	private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
	private static final String TRUE = "true";
	
	Notification notification = new Notification("5d9416921238a33858d16bed", 1, "This is a test notification", null, null, null, false, "Procurement", "Manager", new Date(), new Date());
	
	@Test
	public void TestNotificationCreation() throws Exception {
		String sampleNotification = "{\"message\": \"This is a test notification\",\"purchaseOrder\": null,\"items\": null,\"supplier\": null,\"sender\": \"Procurement\",\"receiverType\": \"Manager\",\"publishedDate\": \"2019-10-02T03:16:33.736+0000\",\"readDate\": null,\"read\": false}";
		Mockito.when(notificationService.saveNotification(Mockito.any(Notification.class))).thenReturn(true);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("http://localhost:9200/api/notification/insert-notification")
				.accept(MediaType.APPLICATION_JSON).content(sampleNotification)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		System.out.println("response: " + result.getResponse().getContentAsString());

		assertEquals(HttpStatus.OK.value(), response.getStatus());
//		assertEquals("application/json", response.getContentType());
//		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	

	
	@Test
	public void TestNotificationUpdation() throws Exception {
		String sampleProcurement = "{\"message\": \"This is a test notification\",\"purchaseOrder\": null,\"items\": null,\"supplier\": null,\"sender\": \"Procurement\",\"receiverType\": \"Manager\",\"publishedDate\": \"2019-10-02T03:16:33.736+0000\",\"readDate\": null,\"read\": true }";
		Mockito.when(notificationService.updateReadStatus(Mockito.any(Notification.class))).thenReturn(true);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("http://localhost:9200/api/notification/read-notification")
				.accept(MediaType.APPLICATION_JSON).content(sampleProcurement)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void TestNotificationDeletion() throws Exception {
		String sampleProcurement = "{\"message\": \"This is a test notification\",\"purchaseOrder\": null,\"items\": null,\"supplier\": null,\"sender\": \"Procurement\",\"receiverType\": \"Manager\",\"publishedDate\": \"2019-10-02T03:16:33.736+0000\",\"readDate\": null,\"read\": false }";
		Mockito.when(notificationService.deleteNotification(Mockito.any(Notification.class))).thenReturn(true);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("http://localhost:9200/api/procurement/delete-procurement")
				.accept(MediaType.APPLICATION_JSON).content(sampleProcurement)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
	}
	
	
	//negative test cases
	@Test
	public void TestNotificationRetrieval() throws Exception {
		
		Mockito.when(notificationService.findByNotificationId(Mockito.anyInt())).thenReturn(notification);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"http://localhost:9200/api/notification/get-notification/1").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		
		String expected = "{\"staffId\": \"S005\", \"staffName\": \"Proc2\", \"staffUserName\": \"Proc_003\", \"staffPassword\": \"Proc125\"}";
		
		//negative conditions
		JSONAssert.assertNotEquals(expected, result.getResponse().getContentAsString(), true);
		
		
	}
}
