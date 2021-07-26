/**
 * 
 */
package com.ss.library.controller;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import com.ss.library.entity.LibraryBranch;
import com.ss.library.service.Librarian;

/**
 * @author Robert Haas
 *
 */
public class BaseController {
	
	static Scanner scan = new Scanner(System.in);
	
	public static LibraryBranch printLibBranchList(){ // Prints the library branches for the user to choose from
		System.out.println("Please enter the number of the Branch from the list");
		
		Librarian<LibraryBranch> lib = new Librarian<LibraryBranch>();
		List<LibraryBranch> list = null;
		AtomicInteger counter = new AtomicInteger();
		
		list = lib.readLibraryBranch(); // Gets the list of library branches in the database
	
		list.forEach(branch -> { // Prints the branches
			counter.getAndIncrement();
			System.out.println(counter + ") " + branch.getBranchName());
		});
		
		int num = list.size() + 1;
		System.out.println(num + ") Quit to previous\n");

		int choice = BaseController.getInt(num);
		if (choice != num) {
			int i = 1;
			for (LibraryBranch branch : list) { // Returns the branch the user chose
				if (i == choice) {
					return branch;
				}
				i++;
			}
		}
		return null;
	}
	
	public static int getInt(int limit) { // Returns the int the user chose
		int num = 0;
		try { // Checks to see if the input was a number
			num = scan.nextInt();
			if (num < 1 || num > limit) { // Makes sure the number is a valid choice
				System.out.println("Please enter a valid choice");
				num = getInt(limit);
			}
		} catch (Exception e) {
			System.out.println("Please enter a number");
			scan.nextLine();
			num = getInt(limit);
		}
		return num;
	}
	
	public static void close() { // Closes the Scanner for this class
		scan.close();
	}
}
