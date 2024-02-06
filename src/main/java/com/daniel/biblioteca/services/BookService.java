package com.daniel.biblioteca.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.biblioteca.DTO.BooksDTO;
import com.daniel.biblioteca.controllers.BookController;
import com.daniel.biblioteca.exceptions.RequiredObjectIsNullException;
import com.daniel.biblioteca.exceptions.ResourceNotFoundException;
import com.daniel.biblioteca.mapper.DozeMapper;
import com.daniel.biblioteca.mappers.custom.UserMapper;
import com.daniel.biblioteca.models.BookStatus;
import com.daniel.biblioteca.models.Books;
import com.daniel.biblioteca.repositories.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private UserMapper mapper;
	
	public List<BooksDTO> findAll() {
		var books = DozeMapper.parseListObjects(bookRepository.findAll(), BooksDTO.class);
		books.stream().forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getId())).withSelfRel()));
		return books;
	}
	
	public BooksDTO findById(Long id) {
		var entity = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		BooksDTO dto = DozeMapper.parseObject(entity, BooksDTO.class);
		dto.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return dto;
	}
	
	public BooksDTO createBook(BooksDTO book) {
		if(book == null) throw new RequiredObjectIsNullException();
		var entity = DozeMapper.parseObject(book, Books.class);
		entity.setName(book.getName());
		entity.setAuthor(book.getAuthor());
		entity.setStatus(BookStatus.AVAILABLE);
		entity = bookRepository.save(entity);
		
		
		var dto = DozeMapper.parseObject(entity, BooksDTO.class);
		dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withSelfRel());
		return dto;
		
	}
	
	public BooksDTO updateBook(BooksDTO book) {
		if(book == null) throw new RequiredObjectIsNullException();
		var entity = bookRepository.findById(book.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		entity.setName(book.getName());
		entity.setAuthor(book.getAuthor());
		
		var dto = DozeMapper.parseObject(entity, BooksDTO.class);
		dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withSelfRel());
		return dto;
		
	}
	
	public BooksDTO updateBookStatus(Long bookId, BookStatus newStatus) {
		var entity = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		entity.setStatus(newStatus);
		bookRepository.save(entity);
		var dto = DozeMapper.parseObject(entity, BooksDTO.class);
	    dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withSelfRel());
	    return dto;
	}
	
	public void delete(Long id) {
		var entity = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		bookRepository.delete(entity);
	}
	
}
