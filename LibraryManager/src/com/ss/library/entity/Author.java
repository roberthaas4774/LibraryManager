/**
 * 
 */
package com.ss.library.entity;

import java.util.List;

/**
 * @author Robert Haas
 *
 */
public class Author {
	
	private int authorID;
	
	private String authorName;
	
	private List<BookAuthors> bookAuthors;
	/**
	 * @return the authorID
	 */
	public int getAuthorID() {
		return authorID;
	}
	/**
	 * @param authorID the authorID to set
	 */
	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}
	/**
	 * @return the authorName
	 */
	public String getAuthorName() {
		return authorName;
	}
	/**
	 * @param authorName the authorName to set
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	/**
	 * @return the bookAuthors
	 */
	public List<BookAuthors> getBookAuthors() {
		return bookAuthors;
	}
	/**
	 * @param bookAuthors the bookAuthors to set
	 */
	public void setBookAuthors(List<BookAuthors> bookAuthors) {
		this.bookAuthors = bookAuthors;
	}
}
