package com.cognizant.jaxrstest.entity;

import javax.json.bind.annotation.JsonbTypeAdapter;

import org.bson.types.ObjectId;

import com.cognizant.jaxrstest.entity.adapters.ObjectIdAdapter;

public class Book {
	@JsonbTypeAdapter(ObjectIdAdapter.class)
	private ObjectId id;
	private String name;
	private String author;
	private double price;
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(String name, String author, double price) {
		super();
		this.name = name;
		this.author = author;
		this.price = price;
	}
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author + ", price=" + price + "]";
	}
	
}
