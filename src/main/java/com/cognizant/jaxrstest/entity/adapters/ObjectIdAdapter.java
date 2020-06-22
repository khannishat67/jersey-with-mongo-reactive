package com.cognizant.jaxrstest.entity.adapters;

import javax.json.bind.adapter.JsonbAdapter;

import org.bson.types.ObjectId;

public class ObjectIdAdapter implements JsonbAdapter<ObjectId, String>{

	@Override
	public String adaptToJson(ObjectId obj) throws Exception {
		
		return obj != null ?  obj.toHexString() : null;
	}

	@Override
	public ObjectId adaptFromJson(String obj) throws Exception {
		return (obj.equals("") || obj == null)? null : new ObjectId(obj);
	}

}
