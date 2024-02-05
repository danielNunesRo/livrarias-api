package com.daniel.biblioteca.unittests.userbookloan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.daniel.biblioteca.models.BookStatus;
import com.daniel.biblioteca.models.Books;
import com.daniel.biblioteca.models.User;
import com.daniel.biblioteca.models.UserBookLoan;

public class UserBookLoanTest {
	
	@Test
	public void testUserBookLoanCreation() {
        User user = new User(1L, "John", "Doe", "johndoe@example.com");
        Books book = new Books(1L, "A Game of Thrones", "George R. R. Martin", BookStatus.AVAILABLE);

        UserBookLoan userBookLoan = new UserBookLoan(1L, user, book);

        assertNotNull(userBookLoan);
        assertEquals(Long.valueOf(1L), userBookLoan.getId());
        assertEquals(user, userBookLoan.getUser());
        assertEquals(book, userBookLoan.getBook());
	}
	
}
