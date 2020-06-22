package com.cognizant.jaxrstest.controllers.subscribers;

import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.Response;

import org.reactivestreams.Subscription;

import com.mongodb.client.result.InsertOneResult;

public class InsertOneSubscriber<T> extends AsyncSubscriber<InsertOneResult> {
	private T obj;
	public InsertOneSubscriber(AsyncResponse asyncResponse, T obj) {
		super(asyncResponse);
		this.obj = obj;
	}
	@Override
	public void onSubscribe(Subscription s) {
		s.request(1);
	}

	@Override
	public void onNext(InsertOneResult t) {
		asyncResponse.resume(Response.status(Response.Status.CREATED).entity(this.obj).build());
	}

	@Override
	public void onError(Throwable t) {
		asyncResponse.resume(Response.serverError().entity(t).build());
	}

	@Override
	public void onComplete() {
		
	}
	
}
