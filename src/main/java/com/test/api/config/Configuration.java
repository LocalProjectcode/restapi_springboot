package com.test.api.config;

import java.util.*;
import java.util.Properties;
import java.io.*;
import java.io.IOException;

/**
 * The ConfigService frees developers from having to hardwire changeable values into 
 * code. Instead, they can be stored externally (in a text file) and read in and used.
 * This allows values to change without requiring code recompilation, thus enhancing the
 * flexibility and maintainability of the code.
 *
 * @author Naresh Kolli
 */
public class Configuration extends Properties implements FileChangeListener {

	private String m_appName = null;
	private static ConfigFileWatchdog watchDog = null;
	static {
		init();
	}
/**
	Default constructor for Configuration
*/

public Configuration() {
    super();
}
/**
 * Initialize the ConfigService with appName
 * @param appName java.lang.String
 * @throws InitializeException
 */
public Configuration(String appName) throws InitializeException {
    this(appName, false);
}
/**
 * Method to reload the file if change is detected.
 * It reloads all the properties based on the appname
 * @concurrency
 */
public synchronized void doOnChange() {
    System.out.println("reloading file: " + m_appName);
    try {
        loadFromFile();
    } catch (Exception e) {
        System.err.println("reloading file failed" + e.getMessage());
    }
}
/**
 * Method to return the current application.
 * @return String	
 */
public String getAppName() {
    return m_appName;
}
/**
 * Static method to initialize the WatchDog thread
 * Creation date: (5/1/2002 3:12:00 PM)
 */
private static void init() {
	if (watchDog == null) {
		watchDog = ConfigFileWatchdog.getInstance();

	}
	if (!watchDog.isAlive()) {
		watchDog.start();
	}
}
/**
 * This method reads the  from the properties file
 * and loads the properties in the Configration object
 * Creation date: (5/1/2002 3:08:53 PM)
 */
private void loadFromFile() throws InitializeException {
    StringTokenizer tokens = new StringTokenizer(getAppName(), ".");
    while (tokens.hasMoreTokens()) {
    	String appName = tokens.nextToken();
    	//load the properties for given application
        String propertiesFileName = appName + ".properties";
        loadFromFile(propertiesFileName);
    }
}
/**
 * This method reads the  from the properties file
 * and loads the properties in the Configration object
 * @param String the name of properties file name
 * @param boolean wether to decrypt the properties in the file or not
 * Creation date: (5/1/2002 3:08:53 PM)
 */
private void loadFromFile(String propertiesFileName)
    throws InitializeException {

    InputStream configFileStream = null;
    try {
        configFileStream = getClass().getClassLoader().getResourceAsStream(propertiesFileName);
//        configFileStream = new FileInputStream(new File("C:\\Users\\567463\\work\\MFA\\props\\otp.properties"));
        if (configFileStream != null) {
                Properties props = new Properties();
                props.load(configFileStream);
                Enumeration e = props.keys();
                while (e.hasMoreElements()) {
                    String key = (String) e.nextElement();
                    String data = props.getProperty(key);
                    setProperty(key, data);
                }
            } else {
                load(configFileStream);
            }
    } catch (IOException ie) {
        ie.printStackTrace();
        throw new InitializeException("could not initialize Config Service - ", ie);
    } finally {
        try {
            if (configFileStream != null)
                configFileStream.close();
            configFileStream = null;
        } catch (IOException ie) {
           
        }
    }
}

/**
 * Initialize the ConfigService with appName
 * @param appName java.lang.String
 * @param addWatchDog boolean to indicate wether to watch the properties file
 * @throws InitializeException
 */
public Configuration(String appName, boolean addWatchDog) throws InitializeException {
    super();
    if (appName != null) {
        m_appName = appName;
        try {
            loadFromFile();
            if ( addWatchDog )
	            ConfigFileWatchdog.getInstance().addListner(this);
        } catch (InitializeException e) {
            throw e;
        }
    } else {
        throw new InitializeException("appName is null", null);
    }
}
}
