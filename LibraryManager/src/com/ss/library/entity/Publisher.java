/**
 * 
 */
package com.ss.library.entity;

import java.util.List;

/**
 * @author Robert Haas
 *
 */
public class Publisher {

	private int publisherID;
	
	private String publisherName;
	private String publisherAddress;
	private String publisherPhone;
	
	private List<Book> book;

	/**
	 * @return the publisherID
	 */
	public int getPublisherID() {
		return publisherID;
	}

	/**
	 * @param publisherID the publisherID to set
	 */
	public void setPublisherID(int publisherID) {
		this.publisherID = publisherID;
	}

	/**
	 * @return the publisherName
	 */
	public String getPublisherName() {
		return publisherName;
	}

	/**
	 * @param publisherName the publisherName to set
	 */
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	/**
	 * @return the publisherAddress
	 */
	public String getPublisherAddress() {
		return publisherAddress;
	}

	/**
	 * @param publisherAddress the publisherAddress to set
	 */
	public void setPublisherAddress(String publisherAddress) {
		this.publisherAddress = publisherAddress;
	}

	/**
	 * @return the publisherPhone
	 */
	public String getPublisherPhone() {
		return publisherPhone;
	}

	/**
	 * @param publisherPhone the publisherPhone to set
	 */
	public void setPublisherPhone(String publisherPhone) {
		this.publisherPhone = publisherPhone;
	}

	/**
	 * @return the book
	 */
	public List<Book> getBook() {
		return book;
	}

	/**
	 * @param book the book to set
	 */
	public void setBook(List<Book> book) {
		this.book = book;
	}
}
