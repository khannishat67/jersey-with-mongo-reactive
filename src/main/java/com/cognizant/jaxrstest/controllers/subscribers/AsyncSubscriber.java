package com.cognizant.jaxrstest.controllers.subscribers;

import javax.ws.rs.container.AsyncResponse;

import org.reactivestreams.Subscriber;

public abstract class AsyncSubscriber<T> implements Subscriber<T> {
	AsyncResponse asyncResponse;
	public AsyncSubscriber(AsyncResponse asyncResponse) {
		this.asyncResponse = asyncResponse;
	}
}
