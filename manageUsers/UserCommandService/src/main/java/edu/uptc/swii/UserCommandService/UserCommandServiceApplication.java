package edu.uptc.swii.UserCommandService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@EnableEurekaServer
@SpringBootApplication
public class UserCommandServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserCommandServiceApplication.class, args);
	}

}
