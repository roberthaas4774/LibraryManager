/**
 * 
 */
package com.ss.library.controller;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import com.ss.library.entity.BookGenres;
import com.ss.library.entity.Genre;
import com.ss.library.service.Admin;

/**
 * @author Robert Haas
 *
 */
public class editGenres {

	static Scanner scan = new Scanner(System.in);

	static Admin<Genre> genre = new Admin<Genre>();
	static Genre bookGenre = new Genre();

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

	public static void add() { // Adds Genres to the database
		System.out.println("Please enter the name of the genre or 'quit' to cancel the operation");
		String name = scan.nextLine();

		if (!"quit".equals(name.toLowerCase())) {
			bookGenre.setGenreName(name);
			genre.addGenre(bookGenre);
		}
		System.out.println();
		func(AdminInput.getFunc());
	}

	public static void update() { // Updates the genres info 
		List<Genre> genreList = genre.readGenres();

		AtomicInteger counter = new AtomicInteger();

		int quit = 0;
		int auth = 0;
		int i = 1;

		System.out.println("Choose a genre to update. Please enter the number next to the genre");
		genreList.forEach(g -> {
			System.out.println(counter.incrementAndGet() + ") " + g.getGenreName());
		});

		quit = genreList.size() + 1;
		System.out.println(quit + ") Quit to previous");
		auth = BaseController.getInt(quit);

		if (auth != quit) {
			for (Genre g : genreList) {
				if (auth == i) {
					bookGenre = g;
				}
				i++;
			}

			System.out.println("Please enter the new name of the genre or enter 'n/a' for no change");
			String name = scan.nextLine();
			if (!"n/a".equals(name.toLowerCase())) {
				bookGenre.setGenreName(name);
				genre.updateGenre(bookGenre);
			}
		}
		System.out.println();
		func(AdminInput.getFunc());
	}

	public static void delete() { // Deletes genres from the database
		List<Genre> genreList = genre.readGenres();

		AtomicInteger counter = new AtomicInteger();

		int quit = 0;
		int auth = 0;
		int i = 1;

		System.out.println("Choose a genre to delete. Please enter the number next to the genre");
		genreList.forEach(g -> {
			System.out.println(counter.incrementAndGet() + ") " + g.getGenreName());
		});

		quit = genreList.size() + 1;
		System.out.println(quit + ") Quit to previous");
		auth = BaseController.getInt(quit);

		if (auth != quit) {
			for (Genre g : genreList) {
				if (auth == i) {
					genre.deleteGenre(g);
				}
				i++;
			}
		}
		System.out.println();
		func(AdminInput.getFunc());
	}

	public static void read() { // Prints all the genres in the database and the books associated with them
		Admin<BookGenres> bookGenre = new Admin<BookGenres>();

		List<Genre> genreList = genre.readGenres();

		System.out.println("-------------------------------------------");
		for (Genre g : genreList) {
			System.out.println("Genre: " + g.getGenreName());

			System.out.print("Book/Books: ");
			readBooks(bookGenre.readBookGenresByGenreId(g.getGenreID()));
			System.out.println("-------------------------------------------");
		}
		System.out.println();
		func(AdminInput.getFunc());
	}

	public static void readBooks(List<BookGenres> list) { // Prints the books each genre has
		int i = 0;

		for (BookGenres bg : list) {
			System.out.print(bg.getBook().getTitle());
			if (i != list.size() - 1) {
				System.out.print(", ");
			}
			i++;
		}
		System.out.println();
	}
}
