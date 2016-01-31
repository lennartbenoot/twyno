package com.twyno.services;


import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.twyno.model.PublicBBQPlace;
import com.twyno.model.Restaurant;

/**
 * OfyService takes care of registering data classes and provides easy access to
 * the Objectify instance.
 * 
 * @author lennart.benoot@mincko.com
 * 
 */
public class OfyService {
	static {
		factory().register(Restaurant.class);
		factory().register(PublicBBQPlace.class);
	}

	public static Objectify ofy() {
		return ObjectifyService.begin();
	}

	public static ObjectifyFactory factory() {
		return ObjectifyService.factory();
	}
}