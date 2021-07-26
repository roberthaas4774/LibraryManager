/**
 * 
 */
package com.ss.library.controller;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import com.ss.library.entity.Book;
import com.ss.library.entity.BookCopies;
import com.ss.library.entity.LibraryBranch;
import com.ss.library.service.Librarian;

/**
 * @author Robert Haas
 *
 */
public class LibraryInput {
	
	static Scanner scan = new Scanner(System.in);

	public static void librarian() {
		System.out.println("Hello librarian. What would you like to do? Please input the number of the choice\n"
				+ "1) Enter Branch you manage\n2) Quit to previous\n" + "3) Close application");

		int num = BaseController.getInt(3);

		switch (num) { // Calls a method depending on the users input
		case 0:
			System.out.println("Incorrect input\n");
			librarian();
			break;
		case 1:
			System.out.println();
			lib2();
			break;
		case 2:
			System.out.println();
			userInput.user();
			break;
		case 3:
			scan.close();
			return;
		}
		scan.close();
	}

	public static void lib2() { // 
		LibraryBranch branch = BaseController.printLibBranchList(); // Gets the library branch the user inputs

		if (branch == null) {
			System.out.println();
			librarian();
		}
		
		else {
			System.out.println();
			lib3(branch);
		}
	}

	public static void lib3(LibraryBranch l) { // Gets the users input and calls a certain method depending on the input
		System.out.println("What would you like to do with the branch? Please input the number of the choice\n"
				+ "1) Update the details of the Library\n2) Add copies of Book to the Branch\n"
				+ "3) Quit to previous\n4) Close application");

		switch (BaseController.getInt(4)) {
		case 0:
			System.out.println("Incorrect input\n");
			lib3(l);
			break;
		case 1:
			System.out.println();
			lib3Op1(l);
			break;
		case 2:
			System.out.println();
			lib3Op2(l);
			break;
		case 3:
			System.out.println();
			lib2();
			break;
		case 4:
			return;
		}
	}

	public static void lib3Op1(LibraryBranch l) { // Updates the library branch the user has chosen
		System.out.println("You have chosen to update the Branch with Branch Id: " + l.getBranchID()
				+ " and Branch Name: " + l.getBranchName() + "\nEnter 'quit' at any prompt to cancel the operation\n");
		System.out.println("Please enter a new branch name or enter N/A for no change:");
		String name = scan.nextLine();

		if (!name.toLowerCase().equals("quit")) {

			System.out.println();
			System.out.println("Please enter a new branch address or enter N/A for no change:");
			String address = scan.nextLine();

			if (!address.toLowerCase().equals("quit")) {
				if (!name.toUpperCase().equals("N/A")) {
					l.setBranchName(name);
				}
				if (!address.toUpperCase().equals("N/A")) {
					l.setBranchAddress(address);
				}

				Librarian<LibraryBranch> lib = new Librarian<LibraryBranch>();
				lib.updateLibraryBranch(l); // Updates the library branch with the info the user inputted
			}
		}

		System.out.println();
		lib3(l);
	}

	public static void lib3Op2(LibraryBranch l) { // Adds copies of books to the library
		Librarian<Book> book = new Librarian<Book>();
		List<Book> bookList = null;
		AtomicInteger counter = new AtomicInteger();
		
		System.out.println("Pick the Book you want to add copies of to your branch. Please input the number of the book");
		
		try {
			bookList = book.readBook();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		bookList.forEach(b -> { // Prints all the books in the database
			System.out.println(counter.incrementAndGet() + ") " + b.getTitle());
		});

		int num = bookList.size() + 1;
		int quit = num + 1;
		System.out.println(num + ") Quit to previous\n");

		int choice = BaseController.getInt(quit);

		if (choice == num) {
			System.out.println();
			lib3(l);
		}
		
		else {
			int i = 1;
			for (Book b : bookList) {
				if (i == choice) {
					copies(l, b); // Gets the book the user chose and sends it to the copies method 
					break;
				}
				i++;
			}
		}
	}
	
	public static void copies(LibraryBranch l, Book b) { // Changes the number of copies the library has of the chosen book
		Librarian<BookCopies> copies = new Librarian<BookCopies>();
		List<BookCopies> copiesList = null;
		BookCopies bCopy = new BookCopies();
		
		int copy = 0;
		boolean empty = false;
		
		copiesList = copies.readCopiesList(b, l); // Gets the book info the library has on the book

		System.out.print("Existing number of copies: "); 
		if (copiesList.size() == 0) { // Prints 0 if the library has no copies of the book
			System.out.println(0);
			empty = true;
		}
		
		for (BookCopies c : copiesList) { // Prints the number of copies the library has of the book
			copy = c.getCopies();
			System.out.println(c.getCopies());
		}
		
		System.out.println("Enter new number of copies: "); 
		
		try {
			copy = scan.nextInt(); // Gets the new number of copies from the user
			if (copy < 0) {
				System.out.println("That was not a valid input. Could not add copies of the book to the branch");
			}
			else {
				if (empty) { // Adds a new row to the book copies table if there is no copies of the book in the library
					bCopy.setBook(b);
					bCopy.setBranch(l);
					bCopy.setCopies(copy);
					copies.addBookCopies(bCopy);
				}
				else {
					for (BookCopies c : copiesList) { // Updates the amount of copies the library has of the book
						c.setCopies(copy);
						copies.updateBookCopies(c);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("That was not a valid input. Could not add copies of the book to the branch");
		}
		
		System.out.println();
		lib3(l);
	}
}
