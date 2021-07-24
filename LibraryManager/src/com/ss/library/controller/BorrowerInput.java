/**
 * 
 */
package com.ss.library.controller;

import java.util.List;
import java.util.Scanner;

import com.ss.library.entity.Book;
import com.ss.library.entity.BookCopies;
import com.ss.library.entity.Borrower;
import com.ss.library.entity.LibraryBranch;
import com.ss.library.service.BorrowerUser;
import com.ss.library.service.Librarian;

/**
 * @author Robert Haas
 *
 */
public class BorrowerInput {

	static Scanner scan = new Scanner(System.in);

	public static void borrower() {
		System.out.println("Enter your Card Number: ");
		int cardNo = getCardNo();
		borr1(cardNo);
		scan.close();
	}

	public static void borr1(int cardNo) {
		System.out.println(
				"What would you like to do? Please input the number of the choice\n1) Check out a book\n2) Return a book"
						+ "\n3) Quit to previous\n4)Close the application");

		switch (BaseController.getInt(4)) {
		case 1:
			System.out.println();
			getBranch(cardNo, 1);
			break;
		case 2:
			System.out.println();
			getBranch(cardNo, 2);
			break;
		case 3:
			System.out.println();
			userInput.user();
			break;
		case 4:
			return;
		}
	}

	public static void getBranch(int cardNo, int choice) {
		LibraryBranch branch = BaseController.printLibBranchList();

		if (branch == null) {
			System.out.println();
			borr1(cardNo);
		}
		
		else {
			System.out.println();
			if (choice == 1) {
				pickCheckOutBook(branch, cardNo);
			}
			
			else {
				pickReturnBook(branch, cardNo);
			}
		}
	}

	public static void pickCheckOutBook(LibraryBranch lb, int cardNo) {
		BorrowerUser<Book> copies = new BorrowerUser<Book>();
		List<BookCopies> copiesList = null;
		final int noCopies = 0;
		try {
			copiesList = copies.readAvailableBooks(lb.getBranchID());
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.println("WTF");
		}
		
		System.out.println("Pick the book you want to check out. Please input the number of the choice");
		copiesList.forEach(c -> {
			System.out.println(c.getBook().getTitle());
		});
	}

	public static void pickReturnBook(LibraryBranch lb, int cardNo) {

	}

	public static int getCardNo() {
		int num = 0;
		BorrowerUser<Borrower> bor = new BorrowerUser<Borrower>();
		List<Borrower> list = null;
		try {
			num = scan.nextInt();

		} catch (Exception e) {
			System.out.println("Please enter a valid Card Number\n");
			scan.next();
			num = getCardNo();
		}

		try {
			list = bor.readBorrowerId(num);
			if (list.size() == 0) {
				System.out.println("Please enter a valid Card Number\n");
				num = getCardNo();
			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.println("Please enter a valid Card Number\n");
			scan.next();
			num = getCardNo();
		}
		return num;
	}
}
