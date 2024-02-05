package com.daniel.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daniel.biblioteca.models.UserBookLoan;

public interface UserBookLoanRepository extends JpaRepository<UserBookLoan, Long> {

}
