/**
 * 
 */
package com.ss.library.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.ss.library.entity.Book;
import com.ss.library.entity.BookCopies;
import com.ss.library.entity.BookLoans;
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
		Borrower bor = getCardNo();
		if (bor == null) {
			userInput.user();
		}
		else {
			borr1(bor);
		}
		scan.close();
	}

	public static void borr1(Borrower bor) {
		System.out.println(
				"What would you like to do? Please input the number of the choice\n1) Check out a book\n2) Return a book"
						+ "\n3) Quit to previous\n4) Close the application");

		switch (BaseController.getInt(4)) {
		case 1:
			System.out.println();
			getBranch(bor, 1);
			break;
		case 2:
			System.out.println();
			getBranch(bor, 2);
			break;
		case 3:
			System.out.println();
			userInput.user();
			break;
		case 4:
			return;
		}
	}

	public static void getBranch(Borrower bor, int choice) {
		LibraryBranch branch = BaseController.printLibBranchList();

		if (branch == null) {
			System.out.println();
			borr1(bor);
		}

		else {
			System.out.println();
			if (choice == 1) {
				pickCheckOutBook(branch, bor);
			}

			else {
				pickReturnBook(branch, bor);
			}
		}
	}

	public static void pickCheckOutBook(LibraryBranch lb, Borrower bor) {	
		BorrowerUser<BookCopies> copies = new BorrowerUser<BookCopies>();
		BorrowerUser<BookLoans> bookLoans = new BorrowerUser<BookLoans>();
		List<BookCopies> copiesList = null;
		BookLoans loan = new BookLoans();

		int num = 0;
		
		copiesList = copies.readAvailableBooks(lb.getBranchID());


		System.out.println("Pick the book you want to check out. Please input the number of the choice");
		for (BookCopies c : copiesList) {
			num++;
			System.out.println(num + ") " + c.getBook().getTitle());
		}
		num++;
		System.out.println(num + ") Quit to cancel operation");

		int choice = BaseController.getInt(num);
		if (choice == num) {
			borr1(bor);
		} 
		
		else {
			int i = 0;
			for (BookCopies c : copiesList) {
				i++;
				if (choice == i) {	
					c.setCopies(c.getCopies() - 1);
					c.setBook(c.getBook());
					
					Date out = new Date();
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					
					int noOfDays = 7; 
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(out);            
					calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
					
					Date due = calendar.getTime();
					df.format(out);
					df.format(due);
					
					loan.setBook(c.getBook());
					loan.setBranch(lb);
					loan.setBorrower(bor);
					
					List<BookLoans> loanList = bookLoans.readBookLoansById(loan);
					loanList.forEach(l -> {
						if (l.getDateIn() != null) {
							copies.updateBookCopies(c);
						}
					});
					
					loan.setDateOut(out);
					loan.setDateDue(due);
					loan.setDateIn(null);
					if(bookLoans.addBookLoans(loan) == false) {
						bookLoans.updateBookLoans(loan);
					}
					break;
				}
			}
		}
		System.out.println();
		borr1(bor);
	}

	public static void pickReturnBook(LibraryBranch lb, Borrower bor) {
		BorrowerUser<BookLoans> loans = new BorrowerUser<BookLoans>();
		BorrowerUser<BookCopies> copies = new BorrowerUser<BookCopies>();
		List<BookLoans> loansList = null;
		BookCopies copy = new BookCopies();

		int num = 0;

		loansList = loans.readLoanedBooks(bor.getCardNo());

		System.out.println("Pick the book you want to return. Please input the number of the choice");
		for (BookLoans b : loansList) {
			num++;
			System.out.println(num + ") " + b.getBook().getTitle());
		}
		num++;
		System.out.println(num + ") Quit to cancel operation");

		int choice = BaseController.getInt(num);
		if (choice == num) {
			borr1(bor);
		} 
		
		else {
			int i = 0;
			for (BookLoans b : loansList) {
				i++;
				if (choice == i) {	
					
					Date in = new Date();
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

					df.format(in);
					b.setDateIn(in);
					
					List<BookCopies> copyList = copies.readCopiesById(b.getBook(), lb);
					copyList.forEach(c -> {
						copy.setCopies(c.getCopies() + 1);
					});
					
					copy.setBook(b.getBook());
					copy.setBranch(lb);
					
					loans.updateBookLoans(b);
					copies.updateBookCopies(copy);
					
					break;
				}
			}
		}
		System.out.println();
		borr1(bor);
	}

	public static Borrower getCardNo() {
		int num = 0;
		BorrowerUser<Borrower> bor = new BorrowerUser<Borrower>();
		List<Borrower> list = null;
		try {
			num = scan.nextInt();
			list = bor.readBorrowerId(num);

			if (list.size() == 0) {
				System.out.println("Please enter a valid Card Number\n");
			}

			else {
				for (Borrower b : list) {
					return b;
				}
			}

		} catch (Exception e) {
			System.out.println("Please enter a valid Card Number\n");
		}
		return null;
	}
}
