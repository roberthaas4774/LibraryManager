/**
 * 
 */
package com.ss.library.controller;

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
public class editBorrower {
	static Scanner scan = new Scanner(System.in);

	static Admin<Borrower> borrCall = new Admin<Borrower>();
	static Borrower borrower = new Borrower();

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
		System.out.println("Please enter the name of the Borrower or 'quit' to cancel the operation");
		String name = scan.nextLine();

		if ("quit".equals(name.toLowerCase())) {
			func(AdminInput.getFunc());
		}

		else {
			System.out.println("Please enter the address of the Borrower or 'quit' to cancel the operation.");
			String address = scan.nextLine();

			if ("quit".equals(address.toLowerCase())) {
				func(AdminInput.getFunc());
			}

			else {
				System.out.println("Please enter the phone number of the Borrower or 'quit' to cancel the operation.");
				String phone = scan.nextLine();

				if ("quit".equals(phone.toLowerCase())) {
					func(AdminInput.getFunc());
				}

				else {
					borrower.setName(name);
					borrower.setAddress(address);
					borrower.setPhoneNo(phone);
					borrCall.addBorrower(borrower);
				}
			}

		}
		func(AdminInput.getFunc());
	}

	public static void update() {
//		Admin<Genre> genre = new Admin<Genre>();
//		Genre bookGenre = new Genre();

		List<Borrower> borrList = borrCall.readBorrower();

		AtomicInteger counter = new AtomicInteger();

		int quit = 0;
		int auth = 0;
		int i = 1;

		System.out.println("Choose a Borrower to update. Please enter the number next to the Borrower");
		borrList.forEach(b -> {
			System.out.println(counter.incrementAndGet() + ") " + b.getName());
		});

		quit = borrList.size() + 1;
		System.out.println(quit + ") Quit to previous");
		auth = BaseController.getInt(quit);

		if (auth != quit) {
			for (Borrower b : borrList) {
				if (auth == i) {
					borrower = b;
				}
				i++;
			}

			System.out.println("Please enter the new name of the Borrower or enter 'n/a' for no change");
			String name = scan.nextLine();
			if (!"n/a".equals(name.toLowerCase())) {
				borrower.setName(name);
			}

			System.out.println("Please enter the new address of the Borrower or enter 'n/a' for no change");
			String address = scan.nextLine();
			if (!"n/a".equals(address.toLowerCase())) {
				borrower.setAddress(address);
			}
			
			System.out.println("Please enter the new phone number of the Borrower or enter 'n/a' for no change");
			String phone = scan.nextLine();
			if (!"n/a".equals(phone.toLowerCase())) {
				borrower.setPhoneNo(phone);
			}

			borrCall.updateBorrower(borrower);
		}
		func(AdminInput.getFunc());
	}

	public static void delete() {
		List<Borrower> borrList = borrCall.readBorrower();

		AtomicInteger counter = new AtomicInteger();

		int quit = 0;
		int auth = 0;
		int i = 1;

		System.out.println("Choose a Borrower to delete. Please enter the number next to the Borrower");
		borrList.forEach(b -> {
			System.out.println(counter.incrementAndGet() + ") " + b.getName());
		});

		quit = borrList.size() + 1;
		System.out.println(quit + ") Quit to previous");
		auth = BaseController.getInt(quit);

		if (auth == quit) {
			func(AdminInput.getFunc());
		}

		for (Borrower b : borrList) {
			if (auth == i) {
				borrCall.deleteBorrower(b);
			}
			i++;
		}

		func(AdminInput.getFunc());
	}

	public static void read() {
		Admin<BookLoans> book = new Admin<BookLoans>();

		List<Borrower> borrList = borrCall.readBorrower();

		System.out.println("\n-------------------------------------------");
		for (Borrower b : borrList) {
			System.out.println("Borrower Name: " + b.getName());
			System.out.println("Address: " + b.getAddress());
			System.out.println("Phone Number: " + b.getPhoneNo());

			readBooks(book.readLoansByCard(b.getCardNo()));
			System.out.println("-------------------------------------------");
		}
		System.out.println();
		func(AdminInput.getFunc());
	}

	public static void readBooks(List<BookLoans> list) {
		for (BookLoans bl : list) {
			System.out.println();
			System.out.println("Book: " + bl.getBook().getTitle() + "\nFrom: " + bl.getBranch().getBranchName() + "\n"
					+ "Date out: " + bl.getDateOut() + "\nDue: " + bl.getDateDue());
			if (bl.getDateIn() != null) {
				System.out.println("Turned in on " + bl.getDateIn());
			}
		}
	}
}
