package edu.uptc.swii.shiftQueryService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@EnableEurekaServer
@SpringBootApplication
public class ShiftQueryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShiftQueryServiceApplication.class, args);
	}

}
