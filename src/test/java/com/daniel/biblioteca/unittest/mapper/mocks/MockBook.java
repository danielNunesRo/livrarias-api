package com.daniel.biblioteca.unittest.mapper.mocks;

import java.util.ArrayList;
import java.util.List;

import com.daniel.biblioteca.DTO.BooksDTO;
import com.daniel.biblioteca.models.BookStatus;
import com.daniel.biblioteca.models.Books;

public class MockBook {

	
	public Books mockEntity () {
		return mockEntity(0);
	}
	
	public BooksDTO mockDTO() {
		return mockDTO(0);
	}
	
	public List<Books> mockEntityList() {
		List<Books> book = new ArrayList<Books>();
		for(int i = 0; i < 14; i++) {
			book.add(mockEntity(i));
		}
		return book;
	}
	
	public List<BooksDTO> mockDTOList() {
		List<BooksDTO> book = new ArrayList<>();
		for(int i = 0; i < 14; i++) {
			book.add(mockDTO(i));
		}
		return book;
		
	}
	
	public Books mockEntity(Integer number) {
		Books book = new Books();
		book.setName("Name Test" + number);
		book.setAuthor("Author Test" + number);
		book.setStatus(BookStatus.AVAILABLE);
		book.setId(number.longValue());
		return book;
	}
	
	public BooksDTO mockDTO(Integer number) {
		BooksDTO book = new BooksDTO();
		book.setName("Name Test" + number);
		book.setAuthor("Author Test" + number);
		book.setStatus(BookStatus.AVAILABLE);
		book.setId(number.longValue());
		return book;
	}
	
	
}
