/**
 * 
 */
package com.ss.library.entity;

import java.util.List;

/**
 * @author Robert Haas
 *
 */
public class LibraryBranch {

	private int branchID;
	
	private String branchName;
	private String branchAddress;
	
	private List<BookLoans> loans;
	private List<BookCopies> bookCopies;
	/**
	 * @return the branchID
	 */
	public int getBranchID() {
		return branchID;
	}
	/**
	 * @param branchID the branchID to set
	 */
	public void setBranchID(int branchID) {
		this.branchID = branchID;
	}
	/**
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}
	/**
	 * @param branchName the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	/**
	 * @return the branchAddress
	 */
	public String getBranchAddress() {
		return branchAddress;
	}
	/**
	 * @param branchAddress the branchAddress to set
	 */
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
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
}
