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
//		Admin<Author> author = new Admin<Author>();
//		Author bookAuthor = new Author();

		System.out.println("Please enter the name of the author or 'quit' to cancel the operation");
		String name = scan.nextLine();
		
		if (!"quit".equals(name.toLowerCase())) {
			bookAuthor.setAuthorName(name);
			author.addAuthor(bookAuthor);
		}
		func(AdminInput.getFunc());
	}

	public static void update() {
//		Admin<Author> author = new Admin<Author>();
//		Author bookAuthor = new Author();
		
		List<Author> authorList = author.readAuthors();

		AtomicInteger counter = new AtomicInteger();

		int quit = 0;
		int auth = 0;
		int i = 1;

		System.out.println("Choose an author to update. Please enter the number next to the author");
		authorList.forEach(a -> {
			System.out.println(counter.incrementAndGet() + ") " + a.getAuthorName());
		});

		quit = authorList.size() + 1;
		System.out.println(quit + ") Quit to previous");
		auth = BaseController.getInt(quit);
		
		if (auth == quit) {
			func(AdminInput.getFunc());
		}
		
		for (Author a : authorList) {
			if (auth == i) {
				bookAuthor = a;
			}
			i++;
		}

		System.out.println("Please enter the new name of the author or enter 'n/a' for no change");
		String name = scan.nextLine();
		
		if ("n/a".equals(name.toLowerCase())) {
			bookAuthor.setAuthorName(name);
			author.updateAuthor(bookAuthor);
		}
		
		func(AdminInput.getFunc());
	}

	public static void delete() {
//		Admin<Author> author = new Admin<Author>();
		
		List<Author> authorList = author.readAuthors();

		AtomicInteger counter = new AtomicInteger();

		int quit = 0;
		int auth = 0;
		int i = 1;

		System.out.println("Choose an author to delete. Please enter the number next to the author");
		authorList.forEach(a -> {
			System.out.println(counter.incrementAndGet() + ") " + a.getAuthorName());
		});

		quit = authorList.size() + 1;
		System.out.println(quit + ") Quit to previous");
		auth = BaseController.getInt(quit);
		
		if (auth == quit) {
			func(AdminInput.getFunc());
		}
		
		for (Author a : authorList) {
			if (auth == i) {
				author.deleteAuthor(a);
			}
			i++;
		}
		
		func(AdminInput.getFunc());
	}

	public static void read() {
//		Admin<Author> author = new Admin<Author>();
		Admin<BookAuthors> bookAuthor = new Admin<BookAuthors>();
		
		List<Author> authorList = author.readAuthors();
		
		System.out.println("\n-------------------------------------------");
		for (Author a : authorList) {
			System.out.println("Author: " + a.getAuthorName());
			
			System.out.print("Book/Books: ");
			readBooks(bookAuthor.readBookAuthorsByAuthorId(a.getAuthorID()));
			System.out.println("\n-------------------------------------------");
		}
		func(AdminInput.getFunc());
	}
	
	public static void readBooks(List<BookAuthors> list) {
		int i = 0;
		
		for (BookAuthors ba : list) {
			System.out.print(ba.getBook().getTitle());
			if (i != list.size() - 1) {
				System.out.print(", ");
			}
			i++;
		}
		System.out.println();
	}
}
