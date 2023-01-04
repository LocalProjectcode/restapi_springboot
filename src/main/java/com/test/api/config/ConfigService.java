package com.test.api.config;


import java.util.Hashtable;

/**
 * Service class which returns the Configuration object based on an
 * application name.
 * Creation date: (5/23/2001 10:23:55 AM)
 * @author: Naresh Kolli
 */
public class ConfigService {
	private static Hashtable configurationMap = new Hashtable();
/**
 * Returns the Configuration object associated with an application name.
 * Creation date: (5/23/2001 10:27:09 AM)
 * @return com.dcx.dealerconnect.config.Configuration
 * @param java.lang.String appName
 */
public static Configuration getConfiguration(String appName) {
	return getConfiguration(appName, true);
}
/**
 * Returns the Configuration object based on an application name 
 * and the option to use the Configuration object as cache.
 * Creation date: (5/23/2001 10:27:09 AM)
 * @return com.dcx.dealerconnect.config.Configuration
 * @param java.lang.String appName
 * @param  boolean useCache
 */
public static Configuration getConfiguration(String appName, boolean useCache) {
	Configuration config = null;
	if ( useCache ) 
	    config = (Configuration)configurationMap.get(appName);
	if ( config == null ) {
		try {
			config = new Configuration(appName, useCache);
			if ( useCache && config.size() > 0 )
				configurationMap.put(appName,config);
		} catch (InitializeException e) {
			e.printStackTrace();
		}
	}
	return config;
}
}
