package com.maloef.cdnd.helloworld.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;

@DynamoDBTable(tableName="Person")
@Data
public class Person {

    @DynamoDBHashKey
    private String id;

    @DynamoDBRangeKey
    private String name;

    @DynamoDBAttribute
    private String dateOfBirth;

}
