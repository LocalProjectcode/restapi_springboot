package com.test.api.config;

/**
 * A WatchDog instance is associated with all the configration files. 
 * The Watchdog checks for any changes to
 * the configuration file periodically.
 */

import java.io.File;
import java.util.*;

public class ConfigFileWatchdog extends Thread {

    /**
    	 The default delay between every file modification check, set to 60
    	 seconds.  
    */
    static final public long DEFAULT_DELAY = 60000;

    /**
    	 The delay to observe between every check. By default set {@link
    	 #DEFAULT_DELAY}. 
    */
    private long delay = DEFAULT_DELAY;

    /**
    	Hashmap of files to watch		
    */
    private static Map watchLists = new HashMap();

    private static final boolean INTERRUPTED = false;

    /**
     * Singleton instance of the ConfigFileWatchDog.		
     */ 
    private static ConfigFileWatchdog watchDog = new ConfigFileWatchdog();
/**
	Default Constructor for ConfigFileWatchdog
*/
private ConfigFileWatchdog() {
    setDaemon(true);
    checkAndConfigure();
}
/**
 * This method is used to add the listener attributes associated with 
 * the configuration file.
 * @param lstnr com.dcx.dealerconnect.config.FileChangeListener
*/

public static void addListner(FileChangeListener lstnr) {
    java.net.URL fileURL=null;
    File file;
    String appName = lstnr.getAppName();
    StringTokenizer tokens = new StringTokenizer(appName, ".");
    while (tokens.hasMoreTokens()) {
        String propertiesFileName = tokens.nextToken() + ".properties";
        FileAppMap map = (FileAppMap) watchLists.get(propertiesFileName);
        if (map == null) {
            fileURL = lstnr.getClass().getClassLoader().getResource(propertiesFileName);
//        	file = new File("C:\\Users\\567463\\work\\MFA\\props\\otp.properties");
//        	try {
//				fileURL = file.toURL();
//			} catch (MalformedURLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
            if (fileURL != null) {
                file = new File(fileURL.getFile());
                if (file.exists()) {
                    map = new FileAppMap(file);
                    map.setLastModifiedTime(file.lastModified());
                    watchLists.put(propertiesFileName, map);
                }
            }
        }
        if (map != null) {
            map.addApp(lstnr);
        }

    }
}
/**
 * This method is called each time the ConfigFileWatchdog wakes up
 * and compares the current timestamp with the timestamp stored in the 
 * hashmap associated with the given file.
*/

private void checkAndConfigure() {
    Iterator itr = watchLists.keySet().iterator();
    while(itr.hasNext()) {
	    String fileName = (String)itr.next();
	    FileAppMap map = (FileAppMap)watchLists.get(fileName);
	    File file = map.getFile();
	    if (file.exists()) {
		    if (file.lastModified() > map.getLastModifiedTime()) {
			    map.setLastModifiedTime(file.lastModified());
			    ArrayList listeners = map.getApps();
			    for (int i = 0; i<listeners.size(); i++) {
				    FileChangeListener lstnr = (FileChangeListener)listeners.get(i);
				    lstnr.doOnChange();
			    }
		    }
	    }
    }
}
/**
 * Returns an instance of ConfigFileWatchdog
 * Creation date: (5/2/2002 3:07:32 PM)
 * @return com.dcx.dealerconnect.config.ConfigFileWatchdog
 */
public static ConfigFileWatchdog getInstance() {
	return watchDog;
}
/**
 * Overridden method of Thread class.
*/
public void run() {
    while ( !INTERRUPTED ) {
        try {
            Thread.currentThread().sleep(delay);
        } catch (InterruptedException e) {
            // no interruption expected
            System.err.println("Configuration File WatchDog Thread is interrupted "+e);
        }

        checkAndConfigure();

    }
}
    /**
      * Set the delay to observe between each check of the file changes.
      * @param long delay - delay associated with periodic runnig of this thread
     */
    public void setDelay(long delay) {
        this.delay = delay;
    }
}
