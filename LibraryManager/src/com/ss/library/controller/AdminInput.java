/**
 * 
 */
package com.ss.library.controller;

import java.util.Scanner;

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
//		case 4:
//			editPublishers();
//			break;
//		case 5:
//			editLibBranches();
//			break;
//		case 6:
//			editBorrowers();
//			break;
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

	}

	public static int getFunc() {
		System.out.println("What function would you like to do? Please input the number of your choice\n1) Add\n"
				+ "2) Update\n3) Delete\n4) Read\n5) Quit to previous\n6) Close Application");
		return (BaseController.getInt(6));
	}
}
