package com.brainext.apps.factchecker;

/**
 * The result of checking a statement
 * @author cpetroaca
 *
 */
public class CheckedStatement {
	/**
	 * The sentence number in which the statement is present
	 */
	private int sentenceNo;
	
	/**
	 * True if the statement is verified and is indeed a fact or false otherwise
	 */
	private boolean isFact;
	
	/**
	 * The text describing the statement
	 */
	private String statementText;
	
	public CheckedStatement(int sentenceNo, boolean isFact, String statementText) {
		if (sentenceNo < 0) {
			throw new IllegalArgumentException("Sentence number cannot be negative");
		}
		
		if (statementText == null || statementText.isEmpty()) {
			throw new IllegalArgumentException("Statement text cannot be null or empty");
		}
		
		this.sentenceNo = sentenceNo;
		this.isFact = isFact;
		this.statementText = statementText;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getSentenceNo() {
		return sentenceNo;
	}
	
	public boolean isFact() {
		return isFact;
	}
	
	public String getStatementText() {
		return statementText;
	}
}
