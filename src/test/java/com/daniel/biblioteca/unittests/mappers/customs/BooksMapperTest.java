package com.daniel.biblioteca.unittests.mappers.customs;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;

import com.daniel.biblioteca.models.Books;
import com.daniel.biblioteca.DTO.BooksDTO;
import com.daniel.biblioteca.mappers.custom.BooksMapper;
import com.daniel.biblioteca.models.BookStatus;


public class BooksMapperTest {

	private final BooksMapper booksMapper = new BooksMapper();
	
	@Test
    public void testConvertEntityToDto() {
        Books book = new Books();
        book.setId(1L);
        book.setName("A Game of Thrones");
        book.setAuthor("George R. R. Martin");
        book.setStatus(BookStatus.AVAILABLE);

        BooksDTO dto = booksMapper.convertEntityToDto(book);

        assertEquals(book.getId(), dto.getId());
        assertEquals(book.getName(), dto.getName());
        assertEquals(book.getAuthor(), dto.getAuthor());
        assertEquals(book.getStatus().toString(), dto.getStatus());
    }
	
	@Test
    public void testConvertDtoToEntity() {
        BooksDTO dto = new BooksDTO(1L, "A Game of Thrones", "George R. R. Martin", "AVAILABLE");

        Books book = booksMapper.convertDtoToEntity(dto);

        assertEquals(dto.getId(), book.getId());
        assertEquals(dto.getName(), book.getName());
        assertEquals(dto.getAuthor(), book.getAuthor());
        assertEquals(BookStatus.valueOf(dto.getStatus()), book.getStatus());
    }
	
}
