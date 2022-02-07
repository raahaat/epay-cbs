package com.era.api;

import com.era.api.util.EncryptDecrypt;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.security.MessageDigest;


import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class EPaymentApplication extends SpringBootServletInitializer {



	public static void main(String[] args) {
		SpringApplication.run(EPaymentApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(EPaymentApplication.class); }

}
