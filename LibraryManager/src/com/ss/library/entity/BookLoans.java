/**
 * 
 */
package com.ss.library.entity;

import java.util.Date;

/**
 * @author Robert Haas
 *
 */
public class BookLoans {

	private Book book;
	private LibraryBranch branch;
	private Borrower borrower;
	
	private Date dateOut;
	private Date dateDue;
	private Date dateIn;
	
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
	 * @return the branch
	 */
	public LibraryBranch getBranch() {
		return branch;
	}
	/**
	 * @param branch the branch to set
	 */
	public void setBranch(LibraryBranch branch) {
		this.branch = branch;
	}
	/**
	 * @return the borrower
	 */
	public Borrower getBorrower() {
		return borrower;
	}
	/**
	 * @param borrower the borrower to set
	 */
	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}
	/**
	 * @return the dateOut
	 */
	public Date getDateOut() {
		return dateOut;
	}
	/**
	 * @param dateOut the dateOut to set
	 */
	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}
	/**
	 * @return the dateDue
	 */
	public Date getDateDue() {
		return dateDue;
	}
	/**
	 * @param dateDue the dateDue to set
	 */
	public void setDateDue(Date dateDue) {
		this.dateDue = dateDue;
	}
	/**
	 * @return the dateIn
	 */
	public Date getDateIn() {
		return dateIn;
	}
	/**
	 * @param dateIn the dateIn to set
	 */
	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}
}
