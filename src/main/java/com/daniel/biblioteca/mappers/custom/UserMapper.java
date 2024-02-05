package com.daniel.biblioteca.mappers.custom;

import org.springframework.stereotype.Service;

import com.daniel.biblioteca.DTO.UserDTO;
import com.daniel.biblioteca.models.User;

@Service
public class UserMapper {
	
	public UserDTO convertEntityToDto(User user) {
		UserDTO vo = new UserDTO();
		vo.setId(user.getId());
		vo.setFirstName(user.getFirstName());
		vo.setLastName(user.getLastName());
		vo.setEmail(user.getEmail());
		return vo;
	}
	
	public User convertDtoToEntity(UserDTO user) {
		User vo = new User();
		vo.setId(user.getId());
		vo.setFirstName(user.getFirstName());
		vo.setLastName(user.getLastName());
		vo.setEmail(user.getEmail());
		return vo;
	}
	
	
}
