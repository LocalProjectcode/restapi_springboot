package com.test.api.config;

/**
 * The InitializeException is thrown by Junax toolbox services when
 * they run into an error at initialize time. This exception extends the
 * base exception, the underlying cause can be saved and re-inspected by 
 * the calling code.
 *
 * Creation date: (5/23/2001 9:43:26 AM)
 * @author: Naresh Kolli
 */
public class InitializeException extends BaseException {
	/**
	 * InitializeException constructor
	 * @param inMessage - descriptive error message indicating the source of the problem
	 * @param inThrowable - the caught exception to be wrapped
	 */
	public InitializeException(String inMessage, Throwable inThrowable) {
		super(inMessage, inThrowable);
	}
}
