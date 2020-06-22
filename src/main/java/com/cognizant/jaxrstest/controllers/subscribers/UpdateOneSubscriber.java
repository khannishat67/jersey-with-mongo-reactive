package com.cognizant.jaxrstest.controllers.subscribers;

import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.Response;

import org.reactivestreams.Subscription;

import com.mongodb.client.result.UpdateResult;

public class UpdateOneSubscriber<T> extends AsyncSubscriber<UpdateResult> {
	private T obj;
	public UpdateOneSubscriber(AsyncResponse asyncResponse, T obj) {
		super(asyncResponse);
		this.obj = obj;
	}
	@Override
	public void onSubscribe(Subscription s) {
		s.request(1);
	}

	@Override
	public void onNext(UpdateResult t) {
		asyncResponse.resume(Response.ok().entity(this.obj).build());
	}

	@Override
	public void onError(Throwable t) {
		t.printStackTrace();
		asyncResponse.resume(Response.serverError().entity(t).build());
	}

	@Override
	public void onComplete() {
		// TODO Auto-generated method stub
		
	}

}
