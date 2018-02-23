package com.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class RpslsGameApplication {
	
	public static void main(String[] args) {
		log.info("Starting RPSLS application");
		
		try {
			new RpslsGameApplication().doStartApplication(args);
		} catch (Exception e) {
			String errorMsg = String.format("Cannot start application due to: [%s]", e.getMessage());
			log.error(errorMsg);
		}
		
	}
	
	private void doStartApplication(final String[] args) {
		final SpringApplication app = new SpringApplication(RpslsGameApplication.class);
		app.setWebEnvironment(true);
		app.run(args);
	}

}
