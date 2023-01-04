package com.test.api.config;

/**
 * Interface to be invoked when a file changes.
 * Creation date: (5/1/2002 3:05:58 PM)
 * @author: Salman Rasheed
 */
public interface FileChangeListener {
/**
 * Abstract method to be invoked on detecting change in File.
 * Creation date: (5/1/2002 3:06:38 PM) 
 */
void doOnChange();
/**
 * Returns the application name.
 * @return param java.lang.String
*/	
String getAppName();
}
