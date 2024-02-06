package com.daniel.biblioteca.unittests.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.daniel.biblioteca.DTO.BooksDTO;
import com.daniel.biblioteca.exceptions.RequiredObjectIsNullException;
import com.daniel.biblioteca.models.BookStatus;
import com.daniel.biblioteca.models.Books;
import com.daniel.biblioteca.repositories.BookRepository;
import com.daniel.biblioteca.services.BookService;
import com.daniel.biblioteca.unittest.mapper.mocks.MockBook;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

	MockBook input;
	
	@InjectMocks
	private BookService service;
	
	@Mock
	BookRepository repository;
	
	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockBook();
		MockitoAnnotations.openMocks(this);
	}
	
	
	@Test
	void testFindAll() {
		List<Books> list = input.mockEntityList();
		when(repository.findAll()).thenReturn(list);
		
		var books = service.findAll();
		assertNotNull(books);
		assertEquals(14, books.size());
		
		var bookOne = books.get(1);
		assertNotNull(bookOne);
		assertNotNull(bookOne.getId());
		assertNotNull(bookOne.getLinks());
		
		var bookSix = books.get(6);
		assertNotNull(bookSix);
		assertNotNull(bookSix.getId());
		assertNotNull(bookSix.getLinks());
	}
	
	@Test
	void testFindById() {
		Books book = input.mockEntity();
		book.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(book));
		var result = service.findById(1L);
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getLinks());
	}
	
	@Test
	void testCreate() {
		Books entity = input.mockEntity();
		Books persisted = entity;
		persisted.setId(1L);
		
		BooksDTO vo = input.mockDTO();
		vo.setId(1L);
		
		var result = service.createBook(vo);
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getLinks());
		
		assertEquals("Name Test0", result.getName());
		assertEquals("Author Test0", result.getAuthor());
		assertEquals(BookStatus.AVAILABLE, result.getStatus());
	}
	
	@Test
	void testCreateWithNullPerson() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.createBook(null);
		});
		
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
		
		
	}
	
	@Test
	void testUpdate() {
		Books entity = input.mockEntity();
		entity.setId(1L);
		
		Books persisted = input.mockEntity();
		persisted.setId(1L);
		
		BooksDTO vo = input.mockDTO(1);
		vo.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		
		var result = service.updateBook(vo);
		
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getLinks());
		
		assertEquals("Name Test1", result.getName());
		assertEquals("Author Test1", result.getAuthor());
		
		
	}
	
	@Test
	void testUpdateWithNullPerson() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.updateBook(null);
		});
		
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testUpdateBookStatus() {
		
		Books entity = input.mockEntity();
		entity.setId(1L);
		
		Books persisted = input.mockEntity();
		persisted.setId(1L);
		
		BooksDTO vo = input.mockDTO(1);
		vo.setId(1L);
		
		 when(repository.findById(1L)).thenReturn(Optional.of(entity));
		
		 BooksDTO resultDTO = service.updateBookStatus(1L, BookStatus.RENTED);
		 assertNotNull(resultDTO);
	     assertNotNull(resultDTO.getId());
	     assertNotNull(resultDTO.getLinks());
	     assertEquals(BookStatus.RENTED, resultDTO.getStatus());
	     
	     verify(repository).save(entity);
		
	}
	
	void testDelete() {
		Books entity = input.mockEntity();
		entity.setId(1L);
		when(repository.findById(1l)).thenReturn(Optional.of(entity));
		service.delete(1L );
	}
	
}
