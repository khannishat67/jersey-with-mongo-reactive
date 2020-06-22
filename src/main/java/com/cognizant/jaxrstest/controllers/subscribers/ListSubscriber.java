package com.cognizant.jaxrstest.controllers.subscribers;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.Response;

import org.reactivestreams.Subscription;

public class ListSubscriber<T> extends AsyncSubscriber<T>{
	private List<T> list;
	private AsyncResponse asyncResponse;
	public ListSubscriber(AsyncResponse asyncResponse) {
		super(asyncResponse);
		this.list = new ArrayList<>();
	}
	@Override
	public void onSubscribe(Subscription s) {
		s.request(Integer.MAX_VALUE);
	}

	@Override
	public void onNext(T t) {
		list.add(t);
	}

	@Override
	public void onError(Throwable t) {
		t.printStackTrace();
		asyncResponse.resume(Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());
	}

	@Override
	public void onComplete() {
		asyncResponse.resume(Response.status(Response.Status.OK).entity(this.list).build()); 
	}
	public List<T> getItems() {
		return this.list;
	}

}
