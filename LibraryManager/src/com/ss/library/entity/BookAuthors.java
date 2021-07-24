/**
 * 
 */
package com.ss.library.entity;

/**
 * @author Robert Haas
 *
 */
public class BookAuthors {
	
	private Book book;
	private LibraryBranch library;
	/**
	 * @return the book
	 */
	public Book getBook() {
		return book;
	}
	/**
	 * @param book the book to set
	 */
	public void setBook(Book book) {
		this.book = book;
	}
	/**
	 * @return the library
	 */
	public LibraryBranch getLibrary() {
		return library;
	}
	/**
	 * @param library the library to set
	 */
	public void setLibrary(LibraryBranch library) {
		this.library = library;
	}
}
