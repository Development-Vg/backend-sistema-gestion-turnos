package edu.uptc.swii.shiftCommandService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ShiftCommandServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShiftCommandServiceApplication.class, args);
	}

}
