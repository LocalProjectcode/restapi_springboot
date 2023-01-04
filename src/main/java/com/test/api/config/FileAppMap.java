package com.test.api.config;

import java.util.*;
import java.io.File;
/**
 * Class to hold the file names, last modified times and
 * application Listeners associated with this file
 * Creation date: (5/3/2002 3:59:08 PM)
 * @author: Frank Yu
 */
final class FileAppMap {
	//Configuration file associated with the application.
	private File file;
	//Modified time of the file.
	private long lastModifiedTime = 0;
	//ArrayList of Listeners associated with the configuration file. 
	private ArrayList apps;
/**
 * FileAppMap constructor. 
 * @param newFile java.io.File - configuration file.
 */
FileAppMap(File newFile) {
	file = newFile;
	apps = new ArrayList();
}
/**
 * Method to add the listener for the application.
 * Creation date: (5/3/2002 5:01:30 PM)
 * @param appName java.lang.String
 */
void addApp(FileChangeListener lstnr) {
	apps.add(lstnr);
}
/**
 * Returns an Array List of applications
 * Creation date: (5/3/2002 5:00:45 PM)
 * @return java.util.ArrayList
 */
ArrayList getApps() {
	return apps;
}
/**
 *	Returns the file associated with this application.
 *  @returns File The file associated with the fileMap
*/
File getFile() {
    return file;
}
/**
 * Returns the last modified file time
 * Creation date: (5/3/2002 4:16:20 PM)
 * @return long
 */
long getLastModifiedTime() {
	return lastModifiedTime;
}
/**
 * Set the last modified time for the file
 * Creation date: (5/3/2002 4:16:20 PM)
 * @param newLastModifiedTime long
 */
void setLastModifiedTime(long newLastModifiedTime) {
	lastModifiedTime = newLastModifiedTime;
}
}
