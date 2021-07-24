package com.ss.library.entity;

import java.util.List;

/**
 * @author Robert Haas
 *
 */
public class Book {
	
	private int bookID;
	private int pubID;
	
	private String title;
	
	private List<BookGenres> genres;
	private List<BookAuthors> authors;
	private List<BookCopies> bookCopies;
	private List<BookLoans> loans;
	/**
	 * @return the bookID
	 */
	public int getBookID() {
		return bookID;
	}
	/**
	 * @param bookID the bookID to set
	 */
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}
	/**
	 * @return the pubID
	 */
	public int getPubID() {
		return pubID;
	}
	/**
	 * @param pubID the pubID to set
	 */
	public void setPubID(int pubID) {
		this.pubID = pubID;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the genres
	 */
	public List<BookGenres> getGenres() {
		return genres;
	}
	/**
	 * @param genres the genres to set
	 */
	public void setGenres(List<BookGenres> genres) {
		this.genres = genres;
	}
	/**
	 * @return the authors
	 */
	public List<BookAuthors> getAuthors() {
		return authors;
	}
	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(List<BookAuthors> authors) {
		this.authors = authors;
	}
	/**
	 * @return the bookCopies
	 */
	public List<BookCopies> getBookCopies() {
		return bookCopies;
	}
	/**
	 * @param bookCopies the bookCopies to set
	 */
	public void setBookCopies(List<BookCopies> bookCopies) {
		this.bookCopies = bookCopies;
	}
	/**
	 * @return the loans
	 */
	public List<BookLoans> getLoans() {
		return loans;
	}
	/**
	 * @param loans the loans to set
	 */
	public void setLoans(List<BookLoans> loans) {
		this.loans = loans;
	}
}
