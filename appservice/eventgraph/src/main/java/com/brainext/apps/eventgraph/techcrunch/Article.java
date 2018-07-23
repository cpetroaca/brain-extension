package com.brainext.apps.eventgraph.techcrunch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.jsonldjava.shaded.com.google.common.base.Strings;

/**
 * Techcrunch article
 * @author cpetroaca
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Article {
	private String title;
	
	private String description;
	
	public Article() {
	}
	
	public Article(String title, String description) {
		if (Strings.isNullOrEmpty(title)) {
			throw new IllegalArgumentException("title cannot be null or empty");
		}
		
		if (Strings.isNullOrEmpty(description)) {
			throw new IllegalArgumentException("description cannot be null or empty");
		}
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
