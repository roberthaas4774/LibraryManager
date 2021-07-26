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
import com.ss.library.service.Admin;

/**
 * @author Robert Haas
 *
 */
public class editLibraryBranches {
	static Scanner scan = new Scanner(System.in);

	static Admin<LibraryBranch> libBranch = new Admin<LibraryBranch>();
	static LibraryBranch branch = new LibraryBranch();

	public static void func(int f) { // Calls a method depending on the users input
		switch (f) {
		case 1:
			System.out.println();
			add();
			break;
		case 2:
			System.out.println();
			update();
			break;
		case 3:
			System.out.println();
			delete();
			break;
		case 4:
			System.out.println();
			read();
			break;
		case 5:
			System.out.println();
			AdminInput.admin();
			break;
		case 6:
			return;
		}
		scan.close();
	}

	public static void add() {	// Adds Library branches to the database
		System.out.println("Please enter the name of the Library Branch or 'quit' to cancel the operation");
		String name = scan.nextLine();

		if (!"quit".equals(name.toLowerCase())) {
			System.out.println("Please enter the address of the Library Branch or 'quit' to cancel the operation.");
			String address = scan.nextLine();

			if (!"quit".equals(address.toLowerCase())) {
				branch.setBranchName(name);
				branch.setBranchAddress(address);
				branch.setBranchID(libBranch.addLibraryBranch(branch));
				addBook(branch); // Adds books to the branch
			}
		}

		System.out.println();
		func(AdminInput.getFunc());	
	}

	public static void update() { // Updates the library branches info and what books are in it
		List<LibraryBranch> branchList = libBranch.readLibraryBranch();

		AtomicInteger counter = new AtomicInteger();
		
		boolean loop = true;

		int quit = 0;
		int auth = 0;
		int i = 1;

		System.out.println("Choose a Library Branch to update. Please enter the number next to the Library Branch");
		branchList.forEach(b -> {
			System.out.println(counter.incrementAndGet() + ") " + b.getBranchName());
		});

		quit = branchList.size() + 1;
		System.out.println(quit + ") Quit to previous");
		auth = BaseController.getInt(quit);

		if (auth != quit) {
			for (LibraryBranch b : branchList) {
				if (auth == i) {
					branch = b;
				}
				i++;
			}

			System.out.println("Please enter the new name of the Library Branch or enter 'n/a' for no change");
			String name = scan.nextLine();
			if (!"n/a".equals(name.toLowerCase())) {
				branch.setBranchName(name);
			}

			System.out.println("Please enter the new address of the Library Branch or enter 'n/a' for no change");
			String address = scan.nextLine();
			if (!"n/a".equals(address.toLowerCase())) {
				branch.setBranchAddress(address);
			}
			libBranch.updateLibraryBranch(branch);
			
			while (loop) {
				System.out.println("Would you like to add/delete books?\n1) Add books to library\n2) Edit the number of copies\n"
						+ "3) Remove books from library\n4) Done");
				int choice = BaseController.getInt(5);
				
				switch (choice) {
				case 1:
					addBook(branch); // Adds books to the library
					break;
				case 2:
					editCopies(choice, branch); // Edit the amount of copies of a book the library has
					break;	
				case 3:
					editCopies(choice, branch); // Removes books from the library
					break;
				}
				if (choice == 4) {
					break;
				}
			}
		}
		System.out.println();
		func(AdminInput.getFunc());
	}

	public static void delete() { // Deletes librarys from the database
		List<LibraryBranch> branchList = libBranch.readLibraryBranch();

		AtomicInteger counter = new AtomicInteger();

		int quit = 0;
		int auth = 0;
		int i = 1;

		System.out.println("Choose a Library Branch to delete. Please enter the number next to the Library Branch");
		branchList.forEach(b -> {
			System.out.println(counter.incrementAndGet() + ") " + b.getBranchName());
		});

		quit = branchList.size() + 1;
		System.out.println(quit + ") Quit to previous");
		auth = BaseController.getInt(quit);

		if (auth == quit) {
			func(AdminInput.getFunc());
		}

		for (LibraryBranch b : branchList) {
			if (auth == i) {
				libBranch.deleteLibraryBranch(b);
			}
			i++;
		}

		func(AdminInput.getFunc());
	}

	public static void read() { // Prints all the libraries in the database and the data associated with it
		Admin<BookCopies> book = new Admin<BookCopies>();

		List<LibraryBranch> branchList = libBranch.readLibraryBranch();

		System.out.println("\n-------------------------------------------");
		for (LibraryBranch b : branchList) {
			System.out.println("Library Branch: " + b.getBranchName());
			System.out.println("Address: " + b.getBranchAddress());

			System.out.print("Book/Books: ");
			readBooks(book.readBooksbyBranch(b.getBranchID()));
			System.out.println("-------------------------------------------");
		}
		func(AdminInput.getFunc());
	}

	public static void readBooks(List<BookCopies> list) { // Prints the books in each library
		int i = 0;

		for (BookCopies bc : list) {
			System.out.print(bc.getBook().getTitle() + " copies " + bc.getCopies());
			if (i != list.size() - 1) {
				System.out.print(", ");
			}
			i++;
		}
		System.out.println();
	}
	
	public static void addBook(LibraryBranch branch) { // Adds books the user chooses to the library
		Admin<Book> bookCall = new Admin<Book>();
		Admin<BookCopies> copyCall = new Admin<BookCopies>();
		BookCopies copy = new BookCopies();
		
		List<Book> bookList = null;
		
		AtomicInteger counter = new AtomicInteger();
		
		System.out.println("Pick the Books you want to add to your branch. Please input the number of the book");

		bookList = bookCall.readBooks();
		bookList.forEach(b -> {
			System.out.println(counter.incrementAndGet() + ") " + b.getTitle());
		});

		int num = bookList.size() + 1;
		int quit = num + 1;
		System.out.println(num + ") Done choosing books\n" + quit + ") Quit to previous\n");

		int choice = BaseController.getInt(quit);
		
		while (choice < num) {
			int i = 1;
			for (Book b : bookList) {
				if (i == choice) {	
					copy.setBook(b);
					copy.setBranch(branch);
					copy.setCopies(numCopies()); // Gets the number of copies the user wants to add to the library and sets it to the noOfCopies value
					copyCall.addBookCopies(copy);
					break;
				}
				i++;
			}
			choice = BaseController.getInt(quit);
		}
		
		if (choice == quit) {
			System.out.println();
			func(AdminInput.getFunc());
		}
	}
	
	public static void editCopies(int i, LibraryBranch branch) { // Edits the amount of copies a library has of a certain book
		Admin<BookCopies> copyCall = new Admin<BookCopies>();
		
		List<BookCopies> bookList = null;
		
		AtomicInteger counter = new AtomicInteger();
		
		if (i == 2) {
			System.out.println("Pick the Books from your branch that you want to edit. Please input the number of the book");
		}
		
		if (i == 3) {
			System.out.println("Pick the Books you want to remove from your branch. Please input the number of the book");
		}

		bookList = copyCall.readBooksbyBranch(branch.getBranchID());

		bookList.forEach(b -> {
			System.out.println(counter.incrementAndGet() + ") " + b.getBook().getTitle());
		});
		int num = bookList.size() + 1;
		int quit = num + 1;
		System.out.println(num + ") Done choosing books\n" + quit + ") Quit to previous\n");

		int choice = BaseController.getInt(quit);
		while (choice < num) {
			int index = 1;
			for (BookCopies b : bookList) {
				if (index == choice) {
					if (i == 2) {
						b.setCopies(numCopies());
						copyCall.updateBookCopies(b); // Edits the amount of copies the library has of a certain book
					}
					
					else {
						copyCall.deleteBookCopies(b); // Removes the book from the library
					}
					break;
				}
				index++;
			}
			choice = BaseController.getInt(quit);
		}
		
		if (choice == quit) {
			System.out.println();
			func(AdminInput.getFunc());
		}
	}
	
	public static int numCopies() { // Gets the number of copies of a book the user wants to add to library
		int numCopy = 1;
		System.out.println("Enter the number of copies?");
		try { // Makes sure the input is a number
			numCopy = scan.nextInt();
			if (numCopy < 0) {
				System.out.println("That was not a valid input. Adding 1 copy");
			}
		} catch (Exception e) {
			System.out.println("That was not a valid input. Adding 1 copy.");
		}
		return numCopy;
	}
}
