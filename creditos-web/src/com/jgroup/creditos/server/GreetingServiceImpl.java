package com.jgroup.creditos.server;

import com.jgroup.creditos.client.GreetingService;
import com.jgroup.creditos.model.Cotizacion;
import com.jgroup.creditos.servicios.ServicioCotizacion;
import com.jgroup.creditos.shared.FieldVerifier;

import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	@EJB 
	private ServicioCotizacion servicioCotizacion;
	
	public String greetServer(String input) throws IllegalArgumentException {
		
		List<Cotizacion> cotizacion = servicioCotizacion.buscarCotizacion(input);
		
		System.out.println("cotizacion: " + cotizacion);
		
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException("Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		return "Hello, " + input + "!<br><br>I am running " + serverInfo + ".<br><br>It looks like you are using:<br>"
				+ userAgent;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
}
