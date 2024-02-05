package com.daniel.biblioteca.mappers.custom;


import org.springframework.stereotype.Service;

import com.daniel.biblioteca.DTO.BooksDTO;
import com.daniel.biblioteca.models.BookStatus;
import com.daniel.biblioteca.models.Books;

@Service
public class BooksMapper {
	
	
	public BooksDTO convertEntityToDto(Books book) {
		BooksDTO vo = new BooksDTO();
		vo.setId(book.getId());
		vo.setName(book.getName());
		vo.setAuthor(book.getAuthor());
		vo.setStatus(book.getStatus().toString());
		
		return vo;
	}
	
	public Books convertDtoToEntity(BooksDTO book) {
		Books vo = new Books();
		vo.setId(book.getId());
		vo.setName(book.getName());
		vo.setAuthor(book.getAuthor());
		vo.setStatus(BookStatus.valueOf(book.getStatus()));
		return vo;
	}
	
}
