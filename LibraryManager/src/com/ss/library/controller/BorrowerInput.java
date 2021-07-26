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

import com.ss.library.entity.BookCopies;
import com.ss.library.entity.BookLoans;
import com.ss.library.entity.Borrower;
import com.ss.library.entity.LibraryBranch;
import com.ss.library.service.BorrowerUser;

/**
 * @author Robert Haas
 *
 */
public class BorrowerInput {

	static Scanner scan = new Scanner(System.in);

	public static void borrower() { // Makes sure the Borrower is a valid user
		System.out.println("Enter your Card Number: ");
		Borrower bor = getCardNo(); // Checks to see if the user exists
		if (bor == null) { // If the card number doesn't exist they go back to the previous menu
			userInput.user();
		}
		else { // Sends the borrowers info to borr1
			borr1(bor);
		}
		scan.close();
	}

	public static void borr1(Borrower bor) { // Checks to see what the borrower wants to do
		System.out.println(
				"What would you like to do? Please input the number of the choice\n1) Check out a book\n2) Return a book"
						+ "\n3) Quit to previous\n4) Close the application");

		switch (BaseController.getInt(4)) { // Gets users choice from the BaseController class getInt method
		case 1: // Sends user to getBranch method with  int 1 indicating they want to checkout a book
			System.out.println();
			getBranch(bor, 1);
			break;
		case 2: // Sends user to getBranch method with  int 1 indicating they want to return a book
			System.out.println();
			getBranch(bor, 2);
			break;
		case 3: // Goes back to main menu
			System.out.println();
			userInput.user();
			break;
		case 4:
			return;
		}
	}

	public static void getBranch(Borrower bor, int choice) {
		LibraryBranch branch = BaseController.printLibBranchList(); // Gets all the library branches

		if (branch == null) { // Goes back to the borr1 method if there are no library branches
			System.out.println();
			borr1(bor);
		}

		else {
			System.out.println();
			if (choice == 1) { // Goes to the method pickCheckOutBook with the branch the user chose and the borrowers info
				pickCheckOutBook(branch, bor);
			}

			else { // Goes to the method pickReturnBook with the branch the user chose and the borrowers info
				pickReturnBook(branch, bor);
			}
		}
	}

	public static void pickCheckOutBook(LibraryBranch lb, Borrower bor) { // Gets the users input and creates a new loan
		BorrowerUser<BookCopies> copies = new BorrowerUser<BookCopies>();
		BorrowerUser<BookLoans> bookLoans = new BorrowerUser<BookLoans>();
		List<BookCopies> copiesList = null;
		BookLoans loan = new BookLoans();

		int num = 0;
		
		copiesList = copies.readAvailableBooks(lb.getBranchID()); // Gets all the books available in the library 


		System.out.println("Pick the book you want to check out. Please input the number of the choice");
		for (BookCopies c : copiesList) { // Prints the available books
			num++;
			System.out.println(num + ") " + c.getBook().getTitle());
		}
		num++;
		System.out.println(num + ") Quit to cancel operation");

		int choice = BaseController.getInt(num);
		if (choice == num) {
			System.out.println();
			borr1(bor);
		} 
		
		else {
			int i = 0;
			for (BookCopies c : copiesList) {
				i++;
				if (choice == i) {	
					c.setCopies(c.getCopies() - 1); // Decreases the book count in the library by 1
					c.setBook(c.getBook()); // Sets the book to the book the user chose
					
					Date out = new Date(); // Gets todays date
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); // Formats the date to year-month-day
					
					int noOfDays = 7; 
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(out);            
					calendar.add(Calendar.DAY_OF_YEAR, noOfDays); 
					
					Date due = calendar.getTime(); // Sets the due date a week from date out
					df.format(out); // Formats the date out
					df.format(due); // Formats the date due
					
					loan.setBook(c.getBook()); // sets all the values in BookLoans
					loan.setBranch(lb);
					loan.setBorrower(bor);
					
					List<BookLoans> loanList = bookLoans.readBookLoansById(loan); // Gets a list of loans the borrower has
					loanList.forEach(l -> {
						if (l.getDateIn() != null) {
							copies.updateBookCopies(c); // Updates the book count if the book is not renewed
						}
					});
					
					loan.setDateOut(out); // sets all the date values in BookLoans
					loan.setDateDue(due);
					loan.setDateIn(null);
					if(bookLoans.addBookLoans(loan) == false) { // New loan is created unless it already exists
						bookLoans.updateBookLoans(loan);
					}
					break;
				}
			}
			System.out.println();
			borr1(bor);
		}
	}

	public static void pickReturnBook(LibraryBranch lb, Borrower bor) {
		BorrowerUser<BookLoans> loans = new BorrowerUser<BookLoans>();
		BorrowerUser<BookCopies> copies = new BorrowerUser<BookCopies>();
		List<BookLoans> loansList = null;
		BookCopies copy = new BookCopies();

		int num = 0;

		loansList = loans.readLoanedBooks(bor.getCardNo()); // Gets loans the user has

		System.out.println("Pick the book you want to return. Please input the number of the choice");
		for (BookLoans b : loansList) { // Prints the loans the user has
			num++;
			System.out.println(num + ") " + b.getBook().getTitle());
		}
		num++;
		System.out.println(num + ") Quit to cancel operation");

		int choice = BaseController.getInt(num);
		if (choice != num) { 
			int i = 0;
			for (BookLoans b : loansList) { 
				i++;
				if (choice == i) { // Gets the loan the user wants to turn in
					
					Date in = new Date(); // Sets date in to todays date
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

					df.format(in); // formats date in
					b.setDateIn(in); // sets the date in, in the loan
					
					List<BookCopies> copyList = copies.readCopiesById(b.getBook(), lb); // Gets the copy of the book in the library 
																						// that it was rented from
					copyList.forEach(c -> { 
						copy.setCopies(c.getCopies() + 1); // Increases the count of the book and sets the BookCopies noOfCopies value to it
					});
					
					copy.setBook(b.getBook()); // Sets the BookCopies Book value to the book that is being turned in
					copy.setBranch(lb); // Sets the BookCopies LibraryBranch value to the LibraryBranch that was chosen
					
					loans.updateBookLoans(b); // Updates the book loan table
					copies.updateBookCopies(copy); // Updates the book copies table
					
					break;
				}
			}
		}
		
		System.out.println();
		borr1(bor);
	}

	public static Borrower getCardNo() { // Checks to see if the user exists
		int num = 0;
		BorrowerUser<Borrower> bor = new BorrowerUser<Borrower>();
		List<Borrower> list = null;
		try { // Checks to see if the input is an int
			num = scan.nextInt();
			list = bor.readBorrowerId(num); // Tries to see if the user exists in the database

			if (list.size() == 0) {
				System.out.println("Please enter a valid Card Number\n");
			}

			else {
				for (Borrower b : list) {
					return b; // Returns the users info if they exist
				}
			}

		} catch (Exception e) {
			System.out.println("Please enter a valid Card Number\n");
		}
		return null; // Returns null if the user does not exist
	}
}
