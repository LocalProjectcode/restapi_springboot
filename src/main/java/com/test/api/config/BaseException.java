package com.test.api.config;

import java.io.PrintStream;
import java.io.PrintWriter;



/**
 * The BaseException class is used across all Junax Toolbox services and 
 * is also suitable for use by developers extending and using the toolbox.
 */
public class BaseException extends Exception
{
	/**
	 * By giving BaseException a reference to a Throwable object,
	 * exception chaining can be enforced easily.
	 */
	private Throwable previousThrowable = null;
	
	/**
	 * Constructs a Base Exception with no specific detailed message 
	 */
	public BaseException()
	{
	}
	/**
	 * Constructs a BaseException with the specific detailed message
	 *
	 * @param inMessage The Detailed message explaining DctBaseException 
	 */
	public BaseException(String inMessage)
	{
		super(inMessage);
	}
	/**
	 * Constructs a BaseException with the specific detailed message and
	 * throwable object so that exceptions can be chained together
	 *
	 * @param inMessage The Detailed message explaining DctBaseException
	 * @param inThrowable The Throwable exception object so that exceptions can be chained
	 */
	public BaseException(String inMessage, Throwable inThrowable)
	{
		super(inMessage);
		this.previousThrowable = inThrowable;
	}
	/**
	 * Constructs a BaseException with no specific detailed message and
	 * throwable object so that exceptions can be chained together
	 *
	 * @param inThrowable The Throwable exception object so that exceptions can be chained
	 */
	public BaseException(Throwable inThrowable)
	{
		this.previousThrowable = inThrowable;
	}
	/**
	 * Prints this exception stack trace to the standard error stream. 
	 * It also prints the chained exception stack traces.
	 */
	public void printStackTrace()
	{
		super.printStackTrace();
		if (this.previousThrowable != null)
		{
			this.previousThrowable.printStackTrace();
		}
	}
	/**
	 * Prints this exception stack and its backtrace to the specified print stream. 
	 * It also prints the chained exception stack traces.
	 *
	 * @param inPrintStream the specific print stream where the stack trace gets printed
	 */
	public void printStackTrace(PrintStream inPrintStream)
	{
		super.printStackTrace(inPrintStream);
		if (this.previousThrowable != null)
		{
			this.previousThrowable.printStackTrace(inPrintStream);
		}
	}
	/**
	 * Prints this exception stack and its backtrace to the specified print writer. 
	 * It also prints the chained exception stack traces.
	 *
	 * @param inPrintWriter the specific printwriter where the stack trace gets filled
	 */
	public void printStackTrace(PrintWriter inPrintWriter)
	{
		super.printStackTrace(inPrintWriter);
		if (this.previousThrowable != null)
		{
			this.previousThrowable.printStackTrace(inPrintWriter);
		}
	}
}
