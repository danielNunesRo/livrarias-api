package com.daniel.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daniel.biblioteca.models.User;


public interface UserRepository extends JpaRepository<User, Long> {

}
