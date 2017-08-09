package com.brainext.core;

/**
 * Excetion thrown by any of the Services defined in the core package
 * @author cpetroaca
 *
 */
public class CoreServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2652191630485336102L;

	public CoreServiceException(Throwable th) {
		super(th);
	}
}
