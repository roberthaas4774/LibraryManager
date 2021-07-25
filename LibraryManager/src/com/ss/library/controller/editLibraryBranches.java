/**
 * 
 */
package com.ss.library.controller;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

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

	public static void func(int f) {
		switch (f) {
		case 1:
			add();
			break;
		case 2:
			update();
			break;
		case 3:
			delete();
			break;
		case 4:
			read();
			break;
		case 5:
			AdminInput.admin();
			break;
		case 6:
			return;
		}
		scan.close();
	}

	public static void add() {
//		Admin<Genre> genre = new Admin<Genre>();
//		Genre bookGenre = new Genre();
		System.out.println("Please enter the name of the Library Branch or 'quit' to cancel the operation");
		String name = scan.nextLine();

		if ("quit".equals(name.toLowerCase())) {
			func(AdminInput.getFunc());
		}

		else {
			System.out.println("Please enter the address of the Library Branch or 'quit' to cancel the operation.");
			String address = scan.nextLine();

			if ("quit".equals(address.toLowerCase())) {
				func(AdminInput.getFunc());
			}

			else {
				branch.setBranchName(name);
				branch.setBranchAddress(address);
				libBranch.addLibraryBranch(branch);
			}

		}
		func(AdminInput.getFunc());
	}

	public static void update() {
//		Admin<Genre> genre = new Admin<Genre>();
//		Genre bookGenre = new Genre();

		List<LibraryBranch> branchList = libBranch.readLibraryBranch();

		AtomicInteger counter = new AtomicInteger();

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
			if ("n/a".equals(name.toLowerCase())) {
				branch.setBranchName(name);
			}

			System.out.println("Please enter the new address of the Library Branch or enter 'n/a' for no change");
			String address = scan.nextLine();
			if ("n/a".equals(address.toLowerCase())) {
				branch.setBranchAddress(address);
			}

			libBranch.updateLibraryBranch(branch);
		}
		func(AdminInput.getFunc());
	}

	public static void delete() {
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

	public static void read() {
		Admin<BookCopies> book = new Admin<BookCopies>();

		List<LibraryBranch> branchList = libBranch.readLibraryBranch();

		System.out.println("\n-------------------------------------------");
		for (LibraryBranch b : branchList) {
			System.out.println("Library Branch: " + b.getBranchName());
			System.out.println("Address: " + b.getBranchAddress());

			System.out.print("Book/Books: ");
			readBooks(book.readBooksbyBranch(b.getBranchID()));
			System.out.println("\n-------------------------------------------");
		}
		func(AdminInput.getFunc());
	}

	public static void readBooks(List<BookCopies> list) {
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
}
