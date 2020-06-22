package com.cognizant.jaxrstest.service;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;
import org.reactivestreams.Publisher;

import com.cognizant.jaxrstest.entity.Book;
import com.cognizant.jaxrstest.repository.BookRepository;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.FindPublisher;

@Service
public class BookService {
	@Inject
	private BookRepository bookRepository;
	
	public Publisher<InsertOneResult> insertBook(Book book) {
		return this.bookRepository.insertBook(book);
	}
	public FindPublisher<Book> getAllBooks() {
		return 	this.bookRepository.getAllBooks();
	}
	public FindPublisher<Book> getBookById(String id) {
		return this.bookRepository.getBookById(id);
	}
	public Publisher<UpdateResult> updateBook(Book book) {
		return this.bookRepository.updateBook(book);
	}
}
