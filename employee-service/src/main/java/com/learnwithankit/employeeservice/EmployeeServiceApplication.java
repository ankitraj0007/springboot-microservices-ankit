package com.learnwithankit.employeeservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableFeignClients
//@EnableEurekaClient
@OpenAPIDefinition(
		info = @Info(
				title = "Employee Service REST APIs",
				description = "Employee Service REST APIs documentation",
				version = "v1.0",
				contact = @Contact(
					name = "Ankit",
					email = "learnwithankit@gmail.com",
					url = "learnwithankit.com"
				),
				license = @License(
					name = "Apache 2.0",
					url = "learnwithankit.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Employee Service REST APIs external documentation",
				url = "learnwithankit.com"
		)
)
public class EmployeeServiceApplication {

	//RestTemplate to make http calls to different api
//	@Bean
//	public RestTemplate restTemplate(){
//		return new RestTemplate();
//	}

	//WebClient to make http calls to different api
	@Bean
	public WebClient webClient(){
		return WebClient.builder().build();
	}


	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

}
