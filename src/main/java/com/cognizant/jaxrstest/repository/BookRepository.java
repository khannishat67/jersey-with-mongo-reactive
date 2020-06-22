package com.cognizant.jaxrstest.repository;

import static com.mongodb.client.model.Filters.eq;

import javax.inject.Inject;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.jvnet.hk2.annotations.Service;
import org.reactivestreams.Publisher;

import com.cognizant.jaxrstest.configuration.MongoDBConfig;
import com.cognizant.jaxrstest.entity.Book;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.FindPublisher;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
@Service
public class BookRepository {
	@Inject
	private MongoDBConfig mongoConfig;
	
	public Publisher<InsertOneResult> insertBook(Book book) {
		MongoCollection<Book> mongoCollection = this.getDatabase().getCollection("book", Book.class);
		return mongoCollection.insertOne(book);
	}

	public FindPublisher<Book> getAllBooks() {
		MongoCollection<Book> mongoCollection = this.getDatabase().getCollection("book", Book.class);
		return mongoCollection.find();
	}
	
	public FindPublisher<Book> getBookById(String id) {
		MongoCollection<Book> mongoCollection = this.getDatabase().getCollection("book", Book.class);
		return mongoCollection.find(eq("_id", new ObjectId(id)));
	}
	
	public Publisher<UpdateResult> updateBook(Book book) {
		MongoCollection<Book> mongoCollection = this.getDatabase().getCollection("book", Book.class);
		return mongoCollection.updateOne(eq("_id", book.getId()), new Document("$set", 	book));
	}
	private MongoDatabase getDatabase() {
		return this.mongoConfig.getMongoDatabase();
	}
}
