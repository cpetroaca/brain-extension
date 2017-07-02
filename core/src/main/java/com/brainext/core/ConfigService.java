package com.brainext.core;

/**
 * Common service for storing configuration parameters that can be used across all apps
 * @author cpetroaca
 *
 */
public interface ConfigService {
	/**
	 * Get the NLP Server URL
	 * @return
	 */
	public String getNlpServerUrl();
}
