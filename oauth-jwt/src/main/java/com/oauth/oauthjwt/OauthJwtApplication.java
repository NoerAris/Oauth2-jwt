package com.oauth.oauthjwt;

import java.util.TimeZone;

import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.convert.Jsr310Converters;

@SpringBootApplication
@EntityScan(basePackageClasses = {
		OauthJwtApplication.class,
		Jsr310Converters.class
})
public class OauthJwtApplication {
	
	private static final Logger log = LoggerFactory.getLogger(OauthJwtApplication.class);
	
	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Jakarta"));
	}
	
	public static void main(String[] args) {
		log.info("Application is started : ");
		SpringApplication.run(OauthJwtApplication.class, args);
	}

}
