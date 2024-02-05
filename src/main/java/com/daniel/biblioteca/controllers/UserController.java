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

import com.daniel.biblioteca.DTO.UserDTO;
import com.daniel.biblioteca.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/users")
@Tag(name= "User", description= "Endpoints for Managing People")
public class UserController {
	
	@Autowired
	private UserService userServices;
	
	@GetMapping
	@Operation(summary = "Finds all users", description = "Finds all users", tags = {"User"}, responses = {
			@ApiResponse(description = "Sucess", responseCode = "200", content = @Content(schema = @Schema(implementation = UserDTO.class)))
			})
	public ResponseEntity<List<UserDTO>> findAll() {
		List<UserDTO> users = userServices.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@Operation(summary = "Finds a User", description = "Finds a User", tags = {"User"},
			responses = {
					@ApiResponse(description = "Sucess", responseCode = "200", content = @Content(schema = @Schema(implementation = UserDTO.class)) )
			})
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
		UserDTO user = userServices.findById(id);
		return ResponseEntity.ok(user);
	}
	
	@Operation(summary = "Adds a new User", description = "Adds a new User passing in JSON.", tags = {"User"}, responses = {
			@ApiResponse(description = "Sucess", responseCode = "200", content = @Content(schema = @Schema(implementation = UserDTO.class)))
	})
	@PostMapping
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
		UserDTO createdUser = userServices.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}
	
	@Operation(summary = "Update a User", description = "Update a User existing by parsing JSON", tags = {"User"}, responses = {
			@ApiResponse(description = "Updated", responseCode = "200", content = @Content(schema = @Schema (implementation = UserDTO.class)))
	})
	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
		userDTO.setId(id);
        UserDTO updatedUser = userServices.update(userDTO);
        return ResponseEntity.ok(updatedUser);
	}
	
	@Operation(summary = "Deletes a User", description = "Deletes a User by passing in Json", tags = {"User"}, responses = {
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        UserDTO deletedUser = userServices.findById(id);
		userServices.delete(id);
		String message = "Usuário com ID " + deletedUser.getId() +
                 "\nNome: " + deletedUser.getFirstName() + " " + deletedUser.getLastName() +
                 " deletado com sucesso";
		 return ResponseEntity.ok(message);
    }

}
