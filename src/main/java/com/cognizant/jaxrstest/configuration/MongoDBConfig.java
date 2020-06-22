package com.cognizant.jaxrstest.configuration;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.jvnet.hk2.annotations.Service;

import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoDatabase;

@Service
public class MongoDBConfig {
	private MongoClient mongoClient;

	public MongoClient getMongoClient() {
		if (mongoClient == null) {
			CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
					CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
			MongoClientSettings settings = MongoClientSettings.builder().codecRegistry(codecRegistry).build();
			this.mongoClient = MongoClients.create(settings);
		}
		return this.mongoClient;
	}
	public MongoDatabase getMongoDatabase() {
		MongoClient mongoClient = this.getMongoClient();
		return mongoClient.getDatabase("mongotest");
	}
}
