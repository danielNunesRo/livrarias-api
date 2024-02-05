package com.daniel.biblioteca.unittests.books;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.daniel.biblioteca.models.BookStatus;
import com.daniel.biblioteca.models.Books;

public class BooksTest {

	@Test
	public void testBookStatus() {
		Books availableBook = new Books(1L, "A Game of Thrones", "George R. R. Martin", BookStatus.AVAILABLE);
		System.out.println("Livro Disponível: " + availableBook.getStatus());
        Books rentedBook = new Books(2L, "The Great Gatsby", "F. Scott Fitzgerald", BookStatus.RENTED);
        System.out.println("Livro Alugado: " + rentedBook.getStatus());
        Books lostBook = new Books(3L, "The Catcher in the Rye", "J.D. Salinger", BookStatus.LOST);
        System.out.println("Livro Perdido: " + lostBook.getStatus());

        assertEquals(BookStatus.AVAILABLE, availableBook.getStatus());
        assertEquals(BookStatus.RENTED, rentedBook.getStatus());
        assertEquals(BookStatus.LOST, lostBook.getStatus());
	}
	
}
