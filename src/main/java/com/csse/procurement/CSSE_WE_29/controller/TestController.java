package com.csse.procurement.CSSE_WE_29.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@RequestMapping("/test/get-value")
	public String getValue() {
		return "Welcome to Test API";
	}
}
