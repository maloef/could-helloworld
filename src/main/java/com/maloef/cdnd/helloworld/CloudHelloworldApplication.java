package com.maloef.cdnd.helloworld;

import com.maloef.cdnd.helloworld.dynamodb.DynamoDBAccess;
import com.maloef.cdnd.helloworld.model.Person;
import com.maloef.cdnd.helloworld.s3.BucketAccess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private BucketAccess bucketAccess;

	@Autowired
	private DynamoDBAccess dynamoDBAccess;

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s, key: %s", name, awsKey);
	}

	@GetMapping("/upload")
	public void upload(@RequestParam(value = "file") String file) {
		bucketAccess.upload(file);
	}

	@GetMapping("/savePerson")
	public void savePerson(@RequestParam(value = "id") String id) {
		Person person = new Person();
		person.setId(id);
		person.setName("Name " + id);
		dynamoDBAccess.savePerson(person);
	}

	@GetMapping("/loadPerson")
	public void loadPerson(@RequestParam(value = "id") String id) {
		Person person = dynamoDBAccess.getPerson(id, "Name " + id);
	}

	public static void main(String[] args) {
		SpringApplication.run(CloudHelloworldApplication.class, args);
	}

}
