package com.cognizant.jaxrstest.controllers.subscribers;

import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.Response;

import org.reactivestreams.Subscription;

public class SingleObjectSubscriber<T> extends AsyncSubscriber<T> {
	
	public SingleObjectSubscriber(AsyncResponse asyncResponse) {
		super(asyncResponse);
	}
	@Override
	public void onSubscribe(Subscription s) {
		s.request(1);
	}
	@Override
	public void onNext(T t) {
		this.asyncResponse.resume(Response.ok().entity(t).build());
	}
	@Override
	public void onError(Throwable t) {
		this.asyncResponse.resume(Response.serverError().entity(t).build());
	}
	@Override
	public void onComplete() {
	
	}
	
}
