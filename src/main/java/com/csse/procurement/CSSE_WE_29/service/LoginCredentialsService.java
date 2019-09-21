package com.csse.procurement.CSSE_WE_29.service;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;

import com.csse.procurement.CSSE_WE_29.entity.LoginCredentials;
import com.csse.procurement.CSSE_WE_29.repository.LoginCredentialsRepository;

@Service
public class LoginCredentialsService {

	@Autowired
	private LoginCredentialsRepository loginCredentialsRepository;
	
	public List<LoginCredentials> findAll() {
		return loginCredentialsRepository.findAll();
	}
	
	public boolean saveLoginCredentials(LoginCredentials loginCredentials, String header) {
		String[] pass_parts = header.split("Basic=", 2);
		String password = pass_parts[1];
		
		loginCredentials.setPassword(password);
		try {
			loginCredentialsRepository.save(loginCredentials);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return true;
	}
	
	public boolean updateLoginCredentials(LoginCredentials loginCredentials) {
		
		try {
			loginCredentialsRepository.save(loginCredentials);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return true;
	}
	
	public boolean deleteLoginCredentials(LoginCredentials loginCredentials) {
		try {
			loginCredentialsRepository.delete(loginCredentials);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return true;
	}
	
	public boolean isUserAvailable(LoginCredentials loginCredentials) {
		String username = loginCredentials.getUsername();
		String userType = loginCredentials.getUserType();
		LoginCredentials user = null;
		user = loginCredentialsRepository.findByUsername(username);
		
		if (user != null) {
			String real_password = user.getPassword();
			String[] pass_parts = loginCredentials.getPassword().split("Basic=", 2);
			String real_userType = user.getUserType();
            String input_password = pass_parts[1];
            
            if (real_password.equals(input_password) && userType.equals(real_userType)) {
            	return true;
            }
            else { 
            	System.out.println("I am here");
            	return false;
            }
			
		}
		return false;
		
	}
}
