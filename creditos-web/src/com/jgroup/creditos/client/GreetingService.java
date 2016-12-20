package com.jgroup.creditos.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
//import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
//@RemoteServiceRelativePath("/greet")
public interface GreetingService extends RemoteService {
	
	public static class Util {
		private static GreetingServiceAsync	instance;
		
		public static GreetingServiceAsync getInstance() {
			if (instance == null) {
				instance = (GreetingServiceAsync) GWT.create(GreetingService.class);
				ServiceDefTarget endpoint = (ServiceDefTarget) instance;
				endpoint.setServiceEntryPoint(GWT.getHostPageBaseURL() + "greet");
			}
			return instance;
		}
	}
	
	
	
	String greetServer(String name) throws IllegalArgumentException;
	
}
