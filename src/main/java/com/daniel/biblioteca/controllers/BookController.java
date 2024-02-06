package com.daniel.biblioteca.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.biblioteca.DTO.BooksDTO;
import com.daniel.biblioteca.models.BookStatus;
import com.daniel.biblioteca.services.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/books")
@Tag(name= "Books", description= "Endpoints for Managing Books")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping
	@Operation(summary = "Finds all Books", description = "Finds all books", tags = {"Book"}, responses = {
			@ApiResponse(description = "Sucess", responseCode = "200", content = @Content(schema = @Schema(implementation = BooksDTO.class)))
			})
	public ResponseEntity<List<BooksDTO>> findAll() {
		List<BooksDTO> books = bookService.findAll();
		return new ResponseEntity<>(books, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Finds a Book", description = "Finds a book", tags = {"Book"}, responses = {
			@ApiResponse(description = "Sucess", responseCode = "200", content = @Content(schema = @Schema(implementation = BooksDTO.class)))
			})
	public ResponseEntity<BooksDTO> findById(@PathVariable Long id) {
		BooksDTO book = bookService.findById(id);
		return ResponseEntity.ok(book);
	}
	
	@PostMapping
	@Operation(summary = "Adds a new Book", description = "Adds a new Book passing in JSON", tags = {"Book"}, responses = {
			@ApiResponse(description = "Sucess", responseCode = "200", content = @Content(schema = @Schema(implementation = BooksDTO.class)))
			})
	public ResponseEntity<BooksDTO> createBook(@RequestBody BooksDTO book) {
		BooksDTO createdBook = bookService.createBook(book);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Update a Book", description = "Update a Book existing", tags = {"Book"}, responses = {
			@ApiResponse(description = "Sucess", responseCode = "200", content = @Content(schema = @Schema(implementation = BooksDTO.class)))
			})
	public ResponseEntity<BooksDTO> updateBook(@PathVariable Long id, @RequestBody BooksDTO book){
		book.setId(id);
		BooksDTO updateBook = bookService.updateBook(book);
		return ResponseEntity.ok(updateBook);
	}
	
	@PutMapping("/{id}/status/{status}")
	@Operation(summary = "Update a Status Book", description = "Update a Status Book existing (AVAILABLE,RENTED,LOST) passing in JSON", tags = {"Book"}, responses = {
			@ApiResponse(description = "Sucess", responseCode = "200", content = @Content(schema = @Schema(implementation = BooksDTO.class)))
			})
    public ResponseEntity<BooksDTO> updateBookStatus(@PathVariable Long id, @PathVariable BookStatus status) {
        BooksDTO updatedBook = bookService.updateBookStatus(id, status);
        return ResponseEntity.ok(updatedBook);
    }
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Deletes a Book", description = "Deletes a Book by passing in Json", tags = {"Book"}, responses = {
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
	})
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        BooksDTO deletedBook = bookService.findById(id);
		bookService.delete(id);
		String message = "Livro com ID: " + deletedBook.getId() +
                 "\nNome: " + deletedBook.getName() + "\nAutor: " + deletedBook.getAuthor() +
                 "\nDeletado com sucesso!";
		 return ResponseEntity.ok(message);
    }
	
	
}
