package com.maloef.cdnd.helloworld.dynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.maloef.cdnd.helloworld.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DynamoDBAccess {

    private DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(AmazonDynamoDBClientBuilder.standard().build());

    public void savePerson(Person person) {
        dynamoDBMapper.save(person);
        log.info("saved person {}", person);
    }

    public Person getPerson(String id, String name) {
        Person loadPerson = new Person();
        loadPerson.setId(id);
        loadPerson.setName(name);

        Person person = dynamoDBMapper.load(loadPerson);
        log.info("loaded person {}", person);
        return person;
    }

}
