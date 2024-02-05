package com.daniel.biblioteca.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_book_loan")
public class UserBookLoan implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date date;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
	private User user;
	
	 @ManyToOne
	 @JoinColumn(name = "book_id")
	private Books book;
	 
	public UserBookLoan() {
		
	}

	public UserBookLoan(Long id, User user, Books book) {
		super();
		this.id = id;
		this.user = user;
		this.book = book;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Books getBook() {
		return book;
	}

	public void setBook(Books book) {
		this.book = book;
	}

	public UserBookLoan(Long id, Date date, User user, Books book) {
		super();
		this.id = id;
		this.date = date;
		this.user = user;
		this.book = book;
	}

	
	
	
	
}
