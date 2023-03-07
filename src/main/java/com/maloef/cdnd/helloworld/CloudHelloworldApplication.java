package com.maloef.cdnd.helloworld;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Slf4j
public class CloudHelloworldApplication {

	@Value("${cloud.aws.credentials.access-key}")
	private String awsKey;

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s, key: %s", name, awsKey);
	}

	public static void main(String[] args) {
		SpringApplication.run(CloudHelloworldApplication.class, args);
	}

}
