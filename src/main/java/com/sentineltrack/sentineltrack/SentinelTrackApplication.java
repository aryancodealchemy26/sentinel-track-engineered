package com.sentineltrack.sentineltrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SentinelTrackApplication {

	public static void main(String[] args) {
		SpringApplication.run(SentinelTrackApplication.class, args);
	}

}
