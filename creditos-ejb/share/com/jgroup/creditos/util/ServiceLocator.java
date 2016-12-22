package com.jgroup.creditos.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceLocator {

	public static final String JBOSS_EJB_CLIENT = "org.jboss.ejb.client.naming";
	public static final String JBOSS_NAMING_CONTEXT = "jboss.naming.client.ejb.context";

	private static Map<String, Object> services = new HashMap<String, Object>();

	private static Context context;

	private static Logger log = LoggerFactory.getLogger(ServiceLocator.class);

	private static final String APP_NAME = "";
	private static final String MODULE_NAME = "creditos-ejb";
	private static final String BEAN_NAME_CONVETION_ADD = "Impl";

	public ServiceLocator() {
		super();
	}

	@SuppressWarnings("unchecked")
	public static <S> S lookupService(Class<S> clazz) {
		if (services.get(clazz.getName()) == null) {
			S service = null;
			try {
				context = getInitialContext();
				String jndi = getLookupName(clazz);
				log.debug("Looking up: " + jndi);
				service = (S) context.lookup(jndi);
				services.put(clazz.getName(), service);
				return service;

			} catch (Exception ex) {
				log.error("Could not get reference to AddressService ", ex);
			} finally {
				if (context != null) {
					try {
						context.close();
					} catch (NamingException e) {
						log.error(e.getMessage(), e);
					}
				}
			}
		}
		return (S) services.get(clazz.getName());
	}

	private static Context getInitialContext() {
		try {
			final Properties jndiProperties = new Properties();
			jndiProperties.put(Context.URL_PKG_PREFIXES, JBOSS_EJB_CLIENT);
			jndiProperties.put(JBOSS_NAMING_CONTEXT, true);
			return new InitialContext(jndiProperties);
		} catch (NamingException e) {
			log.error(e.getMessage(), e);
		}
		return context;
	}

	private static <S> String getLookupName(Class<S> clazz) {
		return "java:global" + APP_NAME + "/" + MODULE_NAME + "/" + clazz.getSimpleName() + BEAN_NAME_CONVETION_ADD + "!"
				+ clazz.getName();
	}
}