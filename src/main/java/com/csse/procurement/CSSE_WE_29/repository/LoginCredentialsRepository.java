package com.csse.procurement.CSSE_WE_29.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.csse.procurement.CSSE_WE_29.entity.LoginCredentials;

@Repository
public interface LoginCredentialsRepository extends MongoRepository<LoginCredentials, String> {
	public LoginCredentials findByUsername(String username);

}
