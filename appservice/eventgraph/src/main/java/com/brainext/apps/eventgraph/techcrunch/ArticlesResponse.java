package com.brainext.apps.eventgraph.techcrunch;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Response from the newsapi endpoint when querying techcrunch articles
 * @author cpetroaca
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticlesResponse {
	private int totalResults;
	
	private List<Article> articles;
	
	public ArticlesResponse() {
	}
	
	public ArticlesResponse(int totalResults, List<Article> articles) {
		if (totalResults < 0) {
			throw new IllegalArgumentException("totalResults cannot be negative");
		}
		
		if (articles == null) {
			throw new IllegalArgumentException("articles cannot be null");
		}
	}
	
	public int getTotalResults() {
		return totalResults;
	}
	
	public List<Article> getArticles() {
		return articles;
	}
	
	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}
	
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
}
