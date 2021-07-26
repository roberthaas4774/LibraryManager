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
import com.ss.library.entity.Borrower;
import com.ss.library.service.Admin;

/**
 * @author Robert Haas
 *
 */
public class AdminInput {

	static Scanner scan = new Scanner(System.in);

	public static void admin() {
		System.out.println(
				"What would you like to do? Please input the number of your choice\n1) Add/Update/Delete/Read Book\n"
						+ "2) Add/Update/Delete/Read Author\n3) Add/Update/Delete/Read Genres\n4) Add/Update/Delete/Read Publishers\n"
						+ "5) Add/Update/Delete/Read Library Branches\n6) Add/Update/Delete/Read Borrowers\n7) Over-ride Due Date for a Book Loan\n"
						+ "8) Quit to previous\n9) Close Application");

		switch (BaseController.getInt(9)) {
		case 1:
			editBook.func(getFunc());
			break;
		case 2:
			editAuthors.func(getFunc());
			break;
		case 3:
			editGenres.func(getFunc());
			break;
		case 4:
			editPublishers.func(getFunc());
			break;
		case 5:
			editLibraryBranches.func(getFunc());
			break;
		case 6:
			editBorrower.func(getFunc());
			break;
		case 7:
			overRide();
			break;
		case 8:
			userInput.user();
			break;
		case 9:
			return;
		}
		scan.close();
	}

	public static void overRide() {
		Admin<BookLoans> loanCall = new Admin<BookLoans>();
		BookLoans loan = new BookLoans();
		
		List<BookLoans> loanList = loanCall.readLoans();

		AtomicInteger counter = new AtomicInteger();

		int quit = 0;
		int auth = 0;
		int i = 1;

		loanList.forEach(l -> {
			if (l.getDateIn() == null) {
				System.out.println("------------------------------------------------------");
				System.out.println(counter.incrementAndGet() + ")\nLibrary Branch: " + l.getBranch().getBranchName() + 
						"\nBorrwer: " + l.getBorrower().getName() + "\nBook: " + l.getBook().getTitle() + "\nDue date: " + l.getDateDue());
			}
		});
		System.out.println("------------------------------------------------------");

		quit = loanList.size() + 1;
		System.out.println(quit + ") Quit to previous\nChoose a Loan to over-ride. Please enter the number associated to the Loan");
		auth = BaseController.getInt(quit);
		if (auth != quit) {
			for (BookLoans bl : loanList) {
				if (bl.getDateIn() != null) {
					continue;
				}
				if (auth == i) {
					loan = bl;
				}
				i++;
			}

			System.out.println("Please enter the new Due Date of the loan or enter 'n/a' for no change\n"
					+ "Please enter the date in the format of YYYY-MM-DD");
			String due = scan.nextLine();

			if (!"n/a".equals(due.toLowerCase())) {
				try {
					Date dueDate = new SimpleDateFormat("yyyy-MM-dd").parse(due); 
					System.out.println(dueDate.toString());
					loan.setDateDue(dueDate);
					loanCall.updateLoan(loan);
				} catch (Exception e) {
					System.out.println("Could not over-ride the due date. Make sure format is correct");
				}
			}
		}
		admin();
	}

	public static int getFunc() {
		System.out.println("What function would you like to do? Please input the number of your choice\n1) Add\n"
				+ "2) Update\n3) Delete\n4) Read\n5) Quit to previous\n6) Close Application");
		return (BaseController.getInt(6));
	}
}
