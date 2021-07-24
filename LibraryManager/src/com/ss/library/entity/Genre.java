/**
 * 
 */
package com.ss.library.entity;

import java.util.List;

/**
 * @author Robert Haas
 *
 */
public class Genre {

	private int genreID;
	
	private String genreName;
	
	private List<BookGenres> bookGenres;

	/**
	 * @return the genreID
	 */
	public int getGenreID() {
		return genreID;
	}

	/**
	 * @param genreID the genreID to set
	 */
	public void setGenreID(int genreID) {
		this.genreID = genreID;
	}

	/**
	 * @return the genreName
	 */
	public String getGenreName() {
		return genreName;
	}

	/**
	 * @param genreName the genreName to set
	 */
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	/**
	 * @return the bookGenres
	 */
	public List<BookGenres> getBookGenres() {
		return bookGenres;
	}

	/**
	 * @param bookGenres the bookGenres to set
	 */
	public void setBookGenres(List<BookGenres> bookGenres) {
		this.bookGenres = bookGenres;
	}
}
