/**
 * 
 */
package com.ss.library.controller;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import com.ss.library.entity.Author;
import com.ss.library.entity.BookAuthors;
import com.ss.library.service.Admin;

/**
 * @author Robert Haas
 *
 */
public class editAuthors {

	static Scanner scan = new Scanner(System.in);
	static Admin<Author> author = new Admin<Author>();
	static Author bookAuthor = new Author();

	public static void func(int f) { // Calls the method depending on the users input
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

	public static void add() { // Adds an author to the database
		System.out.println("Please enter the name of the author or 'quit' to cancel the operation");
		String name = scan.nextLine(); // Gets the authors name

		if (!"quit".equals(name.toLowerCase())) {
			bookAuthor.setAuthorName(name);
			author.addAuthor(bookAuthor); // Adds the author to the database
		}
		System.out.println();
		func(AdminInput.getFunc()); // Calls the previous menu
	}

	public static void update() {
		System.out.println("Choose an author to update. Please enter the number next to the author");

		bookAuthor = getAuthor(); // Gets the author the user chose
		if (bookAuthor != null) {
			System.out.println("Please enter the new name of the author or enter 'n/a' for no change");
			String name = scan.nextLine();

			if (!"n/a".equals(name.toLowerCase())) { // Updates the authors name if n/a was not inputted
				bookAuthor.setAuthorName(name);
				author.updateAuthor(bookAuthor);
			}
		}
		System.out.println();
		func(AdminInput.getFunc()); // Calls the previous menu
	}

	public static void delete() { // Deletes authors the user chooses
		System.out.println("Choose an author to delete. Please enter the number next to the author");

		bookAuthor = getAuthor(); // Gets the author the user chose
		if (bookAuthor != null) {
			author.deleteAuthor(bookAuthor); // Deletes the chosen author
		}
		System.out.println();
		func(AdminInput.getFunc());
	}

	public static void read() { // Returns all the authors in the database and prints them
		Admin<BookAuthors> bookAuthor = new Admin<BookAuthors>();

		List<Author> authorList = author.readAuthors();

		System.out.println("-------------------------------------------");
		for (Author a : authorList) {
			System.out.println("Author: " + a.getAuthorName()); // Prints the authors name

			System.out.print("Book/Books: ");
			readBooks(bookAuthor.readBookAuthorsByAuthorId(a.getAuthorID())); // Gets the books the author has written
			System.out.println("-------------------------------------------");
		}
		System.out.println();
		func(AdminInput.getFunc());
	}

	public static void readBooks(List<BookAuthors> list) { // Gets all the books the author has written and prints them
		int i = 0;

		for (BookAuthors ba : list) {
			System.out.print(ba.getBook().getTitle()); // Prints the books title
			if (i != list.size() - 1) {
				System.out.print(", ");
			}
			i++;
		}
		System.out.println();
	}

	public static Author getAuthor() { // Prints all the authors and returns the author the user chooses
		List<Author> authorList = author.readAuthors(); // Gets all the authors

		AtomicInteger counter = new AtomicInteger();

		int quit = 0;
		int auth = 0;
		int i = 1;

		authorList.forEach(a -> {
			System.out.println(counter.incrementAndGet() + ") " + a.getAuthorName());
		});

		quit = authorList.size() + 1;
		System.out.println(quit + ") Quit to previous");
		auth = BaseController.getInt(quit);

		if (auth != quit) {
			for (Author a : authorList) {
				if (auth == i) {
					return a;
				}
				i++;
			}
		}
		return null;
	}
}
