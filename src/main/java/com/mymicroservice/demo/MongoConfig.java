package com.mymicroservice.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;
import com.mymicroservice.demo.util.Constants;

@Configuration
@EnableMongoRepositories(basePackages = "com.mymicroservice.demo")
public class MongoConfig extends AbstractMongoConfiguration{

	@Override
	public MongoClient mongoClient() {
		return new MongoClient(Constants.DB_HOST, Constants.DB_PORT);
	}

	@Override
	protected String getDatabaseName() {
		return Constants.DB_NAME;
	}
	
}
