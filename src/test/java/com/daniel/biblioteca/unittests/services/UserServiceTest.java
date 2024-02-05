package com.daniel.biblioteca.unittests.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.daniel.biblioteca.DTO.UserDTO;
import com.daniel.biblioteca.exceptions.RequiredObjectIsNullException;
import com.daniel.biblioteca.models.User;
import com.daniel.biblioteca.repositories.UserRepository;
import com.daniel.biblioteca.services.UserService;
import com.daniel.biblioteca.unittest.mapper.mocks.MockUser;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	MockUser input;
	
	@InjectMocks
	private UserService service;
	
	@Mock
	UserRepository repository;
	
	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockUser();
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testFindAll() {
		List<User> list = input.mockEntityList();
		when(repository.findAll()).thenReturn(list);
		
		var users = service.findAll();
		assertNotNull(users);
		assertEquals(14, users.size());
		
		var userOne = users.get(1);
		
		assertNotNull(userOne);
		assertNotNull(userOne.getId());
		assertNotNull(userOne.getLinks());
		
		var userFive = users.get(5);
		
		assertNotNull(userFive);
		assertNotNull(userFive.getId());
		assertNotNull(userFive.getLinks());
	}
	
	@Test
	void testFindById() {
	 User user = input.mockEntity();
	 user.setId(1L);
	 
	 when(repository.findById(1L)).thenReturn(Optional.of(user));
	 var result = service.findById(1L);
	 assertNotNull(result);
	 assertNotNull(result.getId());
	 assertNotNull(result.getLinks());
	}
	
	@Test
	void testCreate() {
		User entity = input.mockEntity(1);
		User persisted = entity;
		persisted.setId(1L);
		
		UserDTO vo = input.mockDTO(1);
		vo.setId(1L);
		
		when(repository.save(entity)).thenReturn(persisted);
		var result = service.createUser(vo);
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getLinks());
		
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Email Test1", result.getEmail());
		
	}
	
	@Test
	void testCreatedWithNullPerson() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.createUser(null);
		});
		
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testUpdate() {
		User entity = input.mockEntity(1);
		entity.setId(1L);
		
		User persisted = entity;
		persisted.setId(1L);
		
		UserDTO vo = input.mockDTO(1);
		vo.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persisted);
		
		
		var result = service.update(vo);
		
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getLinks());
		
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Email Test1", result.getEmail());
	}
	
	@Test
	void testUpdateWithNullPerson() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.update(null);
		});
		
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testDelete() {
		User entity = input.mockEntity(1);
		entity.setId(1L);
		when(repository.findById(1l)).thenReturn(Optional.of(entity));
		
		service.delete(1L);
	}
	
	
	
}
