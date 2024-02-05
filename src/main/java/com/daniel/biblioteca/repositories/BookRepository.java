package com.daniel.biblioteca.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.daniel.biblioteca.models.Books;

public interface BookRepository extends JpaRepository<Books, Long> {

}
