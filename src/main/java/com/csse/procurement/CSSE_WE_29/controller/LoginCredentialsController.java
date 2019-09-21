package com.csse.procurement.CSSE_WE_29.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.csse.procurement.CSSE_WE_29.entity.LoginCredentials;
import com.csse.procurement.CSSE_WE_29.entity.Notification;
import com.csse.procurement.CSSE_WE_29.service.LoginCredentialsService;
import com.csse.procurement.CSSE_WE_29.service.NotificationService;

@Controller
@CrossOrigin(origins = {"http://localhost:4200"}, allowedHeaders = {"authorization", "content-type"})
public class LoginCredentialsController {

	@Autowired
	private LoginCredentialsService loginCredentialsService;
	
	@RequestMapping("api/login/get-all-logins")
	public ResponseEntity<List<LoginCredentials>> getItems() {
		List<LoginCredentials> loginCredentials = null;
		loginCredentials = loginCredentialsService.findAll();
		
		if (loginCredentials != null)
			return new ResponseEntity<List<LoginCredentials>>(loginCredentials, HttpStatus.OK);
		else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "api/login/insert-login")
	public ResponseEntity<Boolean> insertLogin(@RequestBody LoginCredentials loginCredentials, @RequestHeader("Authorization") String header) {
		boolean isInserted = loginCredentialsService.saveLoginCredentials(loginCredentials, header);
		
		if (isInserted)
			return new ResponseEntity<Boolean>(new Boolean(isInserted), HttpStatus.OK);
		else
			return new ResponseEntity<Boolean>(new Boolean(isInserted), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "api/login/verify-user")
	public ResponseEntity<Boolean> verifyUser(@RequestBody LoginCredentials loginCredentials) {
		boolean isUserAvailable = loginCredentialsService.isUserAvailable(loginCredentials);
		
		if (isUserAvailable)
			return new ResponseEntity<Boolean>(new Boolean(isUserAvailable), HttpStatus.OK);
		else
			return new ResponseEntity<Boolean>(new Boolean(isUserAvailable), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "api/login/update-credentials")
	public ResponseEntity<Boolean> readNotification(@RequestBody LoginCredentials loginCredentials) {
		boolean isUpdated = loginCredentialsService.updateLoginCredentials(loginCredentials);
		
		return new ResponseEntity<Boolean>(new Boolean(isUpdated), HttpStatus.OK);
	}
	
}
