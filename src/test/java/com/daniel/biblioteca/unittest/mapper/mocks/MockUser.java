package com.daniel.biblioteca.unittest.mapper.mocks;

import java.util.ArrayList;
import java.util.List;

import com.daniel.biblioteca.DTO.UserDTO;
import com.daniel.biblioteca.models.User;

public class MockUser {

	public User mockEntity() {
		return mockEntity(0);
	}
	
	public UserDTO mockDTO() {
		return mockDTO(0);
	}
	
	public List<User> mockEntityList() {
		List<User> users = new ArrayList<User>();
		for(int i = 0; i < 14; i++) {
			users.add(mockEntity(i));
		}
		return users;
	}
	
	public List<UserDTO> mockDTOList() {
		List<UserDTO> users = new ArrayList<>();
		for(int i = 0; i < 14; i++) {
			users.add(mockDTO(i));
		}
		return users;
		
	}
	
	public User mockEntity(Integer number) {
		User user = new User();
		user.setFirstName("First Name Test" + number);
		user.setLastName("Last Name Test" + number);
		user.setEmail("Email Test" + number);
		user.setId(number.longValue());
		return user;
	}
	
	public UserDTO mockDTO(Integer number) {
		UserDTO user = new UserDTO();
		user.setFirstName("First Name Test" + number);
		user.setLastName("Last Name Test" + number);
		user.setEmail("Email Test" + number);
		user.setId(number.longValue());
		return user;
	}
	
}
