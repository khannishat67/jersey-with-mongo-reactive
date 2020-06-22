package com.cognizant.jaxrstest.controllers;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ManagedAsync;

import com.cognizant.jaxrstest.controllers.subscribers.InsertOneSubscriber;
import com.cognizant.jaxrstest.controllers.subscribers.ListSubscriber;
import com.cognizant.jaxrstest.controllers.subscribers.SingleObjectSubscriber;
import com.cognizant.jaxrstest.controllers.subscribers.UpdateOneSubscriber;
import com.cognizant.jaxrstest.entity.Book;
import com.cognizant.jaxrstest.service.BookService;

@Path("book")
public class BookResource {
	@Inject
	private BookService bookService;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@ManagedAsync
	public void insertBook(Book book, @Suspended final AsyncResponse asyncResponse) {
		InsertOneSubscriber<Book> subscriber = new InsertOneSubscriber<>(asyncResponse, book);
		this.bookService.insertBook(book).subscribe(subscriber);
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	@ManagedAsync
	public void getAllBooks(@Suspended final AsyncResponse asyncResponse) {
		ListSubscriber<Book> subscriber = new ListSubscriber<>(asyncResponse);
		this.bookService.getAllBooks().subscribe(subscriber);
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ManagedAsync
	public void getBookById(@PathParam("id") String id,@Suspended final AsyncResponse asyncResponse) {
		SingleObjectSubscriber<Book> subscriber = new SingleObjectSubscriber<>(asyncResponse);
		this.bookService.getBookById(id).subscribe(subscriber);
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ManagedAsync
	public void updateBook(Book book, @Suspended final AsyncResponse asyncResponse) {
		UpdateOneSubscriber<Book> subscriber = new UpdateOneSubscriber<Book>(asyncResponse, book);
		this.bookService.updateBook(book).subscribe(subscriber);;
	}
}
