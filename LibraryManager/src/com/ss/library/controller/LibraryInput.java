/**
 * 
 */
package com.ss.library.controller;

import java.util.List;
import java.util.Scanner;

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

		switch (num) {
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

	public static void lib2() {
		LibraryBranch branch = BaseController.printLibBranchList();

		if (branch == null) {
			System.out.println();
			librarian();
		}
		
		else {
			System.out.println();
			lib3(branch);
		}
	}

	public static void lib3(LibraryBranch l) {
		System.out.println("What would you like to do with the branch? Please input the number of the choice\n"
				+ "1) Update the details of the Library\n2) Add copies of Book to the Branch\n"
				+ "3) Quit to previous\n4) Close application");

		int num = BaseController.getInt(4);
		switch (num) {
		case 0:
			System.out.println("Incorrect input\n");
			lib3(l);
			break;
		case 1:
			System.out.println();
			scan.nextLine();
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

	public static void lib3Op1(LibraryBranch l) {
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
				lib.updateLibraryBranch(l);
			}
		}

		System.out.println();
		lib3(l);
	}

	public static void lib3Op2(LibraryBranch l) {
		System.out.println("-------------------------------------------------\n");
		
		Librarian<Book> book = new Librarian<Book>();
		List<Book> bookList = null;
		try {
			bookList = book.readBook();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		bookList.forEach(b -> {
			System.out.println(b.getBookID() + ") " + b.getTitle());
		});

		int num = bookList.size() + 1;
		int quit = num + 1;
		System.out.println(num + ") Quit to previous\n" + quit + ") Close application\n");
		System.out.println(
				"Pick the Book you want to add copies of to your branch from the list above. Please input the number of the book");

		int choice = BaseController.getInt(quit);

		if (choice == num) {
			System.out.println();
			lib3(l);
		}
		
		else if (choice == quit) {
			return;
		}
		
		else {
			bookList.forEach(b -> {
				if (choice == b.getBookID()) {
					System.out.println();
					copies(l, b);
				}
			});
		}

	}
	
	public static void copies(LibraryBranch l, Book b) {
		Librarian<BookCopies> copies = new Librarian<BookCopies>();
		List<BookCopies> copiesList = null;
		BookCopies bCopy = new BookCopies();
		
		int copy = 0;
		boolean empty = false;
		
		try {
			copiesList = copies.readCopiesList(b, l);
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.print("Existing number of copies: ");
		if (copiesList.size() == 0) {
			System.out.println(0);
			empty = true;
		}
		
		for (BookCopies c : copiesList) {
			copy = c.getCopies();
			System.out.println(c.getCopies());
		}
		
		System.out.println("Enter new number of copies: "); 
		
		try {
			copy = scan.nextInt();
			if (copy < 0) {
				System.out.println("That was not a valid input. Could not add copies of the book to the branch");
			}
			else {
				if (empty) {
					bCopy.setBook(b);
					bCopy.setBranch(l);
					bCopy.setCopies(copy);
					copies.addBookCopies(bCopy);
				}
				else {
					for (BookCopies c : copiesList) {
						c.setCopies(copy);
						copies.updateBookCopies(c);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("That was not a valid input. Could not add copies of the book to the branch");
		}
		
		lib3(l);
	}
}
