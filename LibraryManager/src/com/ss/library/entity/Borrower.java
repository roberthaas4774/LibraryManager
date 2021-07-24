/**
 * 
 */
package com.ss.library.entity;

import java.util.List;

/**
 * @author Robert Haas
 *
 */
public class Borrower {

	private int cardNo;
	
	private String name;
	private String address;
	private String phoneNo;
	
	private List<BookLoans> loans;

	/**
	 * @return the cardNo
	 */
	public int getCardNo() {
		return cardNo;
	}

	/**
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * @param phoneNo the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
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
