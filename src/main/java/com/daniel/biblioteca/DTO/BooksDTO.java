package com.daniel.biblioteca.DTO;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.daniel.biblioteca.models.BookStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;

public class BooksDTO extends RepresentationModel<UserDTO> implements Serializable {
	 	
		private static final long serialVersionUID = 1L;
		
		@Mapping("id")
		@JsonProperty("id")
		private Long id;
	    private String name;
	    private String author;
	    private BookStatus status;

	    public BooksDTO() {
	    }

	    public BooksDTO(Long id, String name, String author, BookStatus status) {
	        this.id = id;
	        this.name = name;
	        this.author = author;
	        this.status = status;
	    }

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getAuthor() {
	        return author;
	    }

	    public void setAuthor(String author) {
	        this.author = author;
	    }

		public BookStatus getStatus() {
			return status;
		}

		public void setStatus(BookStatus status) {
			this.status = status;
		}

	   
}
