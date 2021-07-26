/**
 * 
 */
package com.ss.library.controller;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import com.ss.library.entity.Book;
import com.ss.library.entity.Publisher;
import com.ss.library.service.Admin;

/**
 * @author Robert Haas
 *
 */
public class editPublishers {
	static Scanner scan = new Scanner(System.in);

	static Admin<Publisher> publisher = new Admin<Publisher>();
	static Publisher bookPublisher = new Publisher();

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

	public static void add() { // Adds publishers to the database
		System.out.println("Please enter the name of the Publisher or 'quit' to cancel the operation");
		String name = scan.nextLine();

		if ("quit".equals(name.toLowerCase())) {
			func(AdminInput.getFunc());
		}

		else {
			System.out.println("Please enter the address of the Publisher or 'quit' to cancel the operation.");
			String address = scan.nextLine();

			if ("quit".equals(name.toLowerCase())) {
				func(AdminInput.getFunc());
			}

			else {
				System.out.println("Please enter the phone number of the Publisher or 'quit' to cancel the operation.");
				String phone = scan.nextLine();

				if ("quit".equals(name.toLowerCase())) {
					func(AdminInput.getFunc());
				}

				else { // Sets all the values for publisher and calls the method to add it to the database
					bookPublisher.setPublisherName(name);
					bookPublisher.setPublisherAddress(address);
					bookPublisher.setPublisherPhone(phone);
					publisher.addPublisher(bookPublisher); 
				}
			}

		}
		func(AdminInput.getFunc());
	}

	public static void update() { // Updates the values of the Publisher
		List<Publisher> publisherList = publisher.readPubs();

		AtomicInteger counter = new AtomicInteger();

		int quit = 0;
		int auth = 0;
		int i = 1;

		System.out.println("Choose a Publisher to update. Please enter the number next to the Publisher");
		publisherList.forEach(p -> {
			System.out.println(counter.incrementAndGet() + ") " + p.getPublisherName());
		});

		quit = publisherList.size() + 1;
		System.out.println(quit + ") Quit to previous");
		auth = BaseController.getInt(quit);

		if (auth != quit) {
			for (Publisher p : publisherList) {
				if (auth == i) {
					bookPublisher = p;
				}
				i++;
			}

			System.out.println("Please enter the new name of the Publisher or enter 'n/a' for no change");
			String name = scan.nextLine();
			if (!"n/a".equals(name.toLowerCase())) {
				bookPublisher.setPublisherName(name); // Sets the new name
			}

			System.out.println("Please enter the new address of the Publisher or enter 'n/a' for no change");
			String address = scan.nextLine();
			if (!"n/a".equals(address.toLowerCase())) {
				bookPublisher.setPublisherAddress(address); // Sets the new address
			}

			System.out.println("Please enter the new phone number of the Publisher or enter 'n/a' for no change");
			String phone = scan.nextLine();
			if (!"n/a".equals(phone.toLowerCase())) { // Sets the new phone number
				bookPublisher.setPublisherPhone(phone);
			}

			publisher.updatePublisher(bookPublisher); // Calls the method to update the publishers info
		}
		System.out.println();
		func(AdminInput.getFunc());
	}

	public static void delete() { // Deletes publishers from the database
		List<Publisher> publisherList = publisher.readPubs();

		AtomicInteger counter = new AtomicInteger();

		int quit = 0;
		int auth = 0;
		int i = 1;

		System.out.println("Choose a Publisher to delete. Please enter the number next to the publisher");
		publisherList.forEach(p -> {
			System.out.println(counter.incrementAndGet() + ") " + p.getPublisherName());
		});

		quit = publisherList.size() + 1;
		System.out.println(quit + ") Quit to previous");
		auth = BaseController.getInt(quit);

		if (auth != quit) {
			for (Publisher p : publisherList) {
				if (auth == i) {
					publisher.deletePublisher(p); // Deletes the chosen publisher
				}
				i++;
			}
		}
		System.out.println();
		func(AdminInput.getFunc());
	}

	public static void read() { // Prints out all the publishers and the info associated with them
		Admin<Book> book = new Admin<Book>();

		List<Publisher> publisherList = publisher.readPubs();

		System.out.println("\n-------------------------------------------");
		for (Publisher p : publisherList) {
			System.out.println("Publisher: " + p.getPublisherName());
			System.out.println("Address: " + p.getPublisherAddress());
			System.out.println("Phone Number: " + p.getPublisherPhone());

			System.out.print("Book/Books: ");
			readBooks(book.readBooksByPubId(p.getPublisherID()));
			System.out.println("-------------------------------------------");
		}
		System.out.println();
		func(AdminInput.getFunc());
	}

	public static void readBooks(List<Book> list) { // Prints the books the publisher has published
		int i = 0;

		for (Book b : list) {
			System.out.print(b.getTitle());
			if (i != list.size() - 1) {
				System.out.print(", ");
			}
			i++;
		}
		System.out.println();
	}
}
