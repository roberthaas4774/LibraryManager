/**
 * 
 */
package com.ss.library.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import com.ss.library.entity.BookLoans;
import com.ss.library.service.Admin;

/**
 * @author Robert Haas
 *
 */
public class AdminInput { // This is for the admins inputs

	static Scanner scan = new Scanner(System.in);

	public static void admin() { // User chooses what hey want to do and based on the input it goes to another method
		System.out.println(
				"What would you like to do? Please input the number of your choice\n1) Add/Update/Delete/Read Book\n"
						+ "2) Add/Update/Delete/Read Author\n3) Add/Update/Delete/Read Genres\n4) Add/Update/Delete/Read Publishers\n"
						+ "5) Add/Update/Delete/Read Library Branches\n6) Add/Update/Delete/Read Borrowers\n7) Over-ride Due Date for a Book Loan\n"
						+ "8) Quit to previous\n9) Close Application");

		switch (BaseController.getInt(9)) {
		case 1: // Goes to editBook class and calls the func method
			System.out.println();
			editBook.func(getFunc());
			break;
		case 2: // Goes to editAuthors class and calls the func method
			System.out.println();
			editAuthors.func(getFunc());
			break;
		case 3: // Goes to editGenres class and calls the func method
			System.out.println();
			editGenres.func(getFunc());
			break;
		case 4: // Goes to editPublishers class and calls the func method
			System.out.println();
			editPublishers.func(getFunc());
			break;
		case 5: // Goes to editLibraryBranches class and calls the func method
			System.out.println();
			editLibraryBranches.func(getFunc());
			break;
		case 6: // Goes to editBorrower class and calls the func method
			System.out.println();
			editBorrower.func(getFunc());
			break;
		case 7: // Calls the overRide method
			System.out.println();
			overRide();
			break;
		case 8: // Goes to the userInput class and calls the user method
			System.out.println();
			userInput.user();
			break;
		case 9: // Does nothing, but it closes the Scanner and returns to previous which ends the program
			break;
		}
		scan.close();
		return;
	}

	public static void overRide() { // Over rides the due date
		Admin<BookLoans> loanCall = new Admin<BookLoans>();
		BookLoans loan = new BookLoans();
		
		List<BookLoans> loanList = loanCall.readLoans(); // Gets all the loans in the database

		AtomicInteger counter = new AtomicInteger();

		int quit = 0;
		int auth = 0;
		int i = 1;

		loanList.forEach(l -> { // Loops through the list and prints loans that have not been turned in yet
			if (l.getDateIn() == null) {
				System.out.println("------------------------------------------------------");
				System.out.println(counter.incrementAndGet() + ")\nLibrary Branch: " + l.getBranch().getBranchName() + 
						"\nBorrwer: " + l.getBorrower().getName() + "\nBook: " + l.getBook().getTitle() + "\nDue date: " + l.getDateDue());
			}
		});
		System.out.println("------------------------------------------------------");

		quit = loanList.size() + 1;
		System.out.println(quit + ") Quit to previous\nChoose a Loan to over-ride. Please enter the number associated to the Loan");
		auth = BaseController.getInt(quit); // Calls the getInt method in the BaseController class that returns an int 
		if (auth != quit) { 
			for (BookLoans bl : loanList) { 
				if (bl.getDateIn() != null) { // Skips the loans that have been turned in
					continue;
				}
				if (auth == i) { // Gets the loan that the user selected
					loan = bl;
				}
				i++;
			}

			System.out.println("Please enter the new Due Date of the loan or enter 'n/a' for no change\n"
					+ "Please enter the date in the format of YYYY-MM-DD");
			String due = scan.nextLine();

			if (!"n/a".equals(due.toLowerCase())) {
				try {
					Date dueDate = new SimpleDateFormat("yyyy-MM-dd").parse(due); // Converts the string date to a date object
					System.out.println(dueDate.toString());
					loan.setDateDue(dueDate); // Sets the due date to what the user inputted
					loanCall.updateLoan(loan); // Updates the due date for the loan
				} catch (Exception e) {
					System.out.println("Could not over-ride the due date. Make sure format is correct");
				}
			}
		}
		System.out.println();
		admin();
	}

	public static int getFunc() { // Prints options for the user and returns the users choice
		System.out.println("What function would you like to do? Please input the number of your choice\n1) Add\n"
				+ "2) Update\n3) Delete\n4) Read\n5) Quit to previous\n6) Close Application");
		return (BaseController.getInt(6));
	}
}
