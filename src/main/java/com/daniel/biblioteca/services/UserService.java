package com.daniel.biblioteca.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.biblioteca.DTO.UserDTO;
import com.daniel.biblioteca.controllers.UserController;
import com.daniel.biblioteca.exceptions.RequiredObjectIsNullException;
import com.daniel.biblioteca.exceptions.ResourceNotFoundException;
import com.daniel.biblioteca.mapper.DozeMapper;
import com.daniel.biblioteca.mappers.custom.UserMapper;
import com.daniel.biblioteca.models.User;
import com.daniel.biblioteca.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper mapper;
	
	public List<UserDTO> findAll() {
		var users = DozeMapper.parseListObjects(userRepository.findAll(), UserDTO.class);
		users.stream().forEach(p -> p.add(linkTo(methodOn(UserController.class).findById(p.getId())).withSelfRel()));
		return users;
	}
	
	public UserDTO findById(Long id) {
		var entity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		UserDTO dto = DozeMapper.parseObject(entity, UserDTO.class);
		dto.add(linkTo(methodOn(UserController.class).findById(id)).withSelfRel());
		return dto;
	}
	
	public UserDTO createUser(UserDTO user) {
		if(user == null) throw new RequiredObjectIsNullException();
		var entity = DozeMapper.parseObject(user, User.class);
		entity.setFirstName(user.getFirstName());
		entity.setLastName(user.getLastName());
		entity.setEmail(user.getEmail());
		
		var dto = DozeMapper.parseObject(userRepository.save(entity), UserDTO.class);
		dto.add(linkTo(methodOn(UserController.class).findById(dto.getId())).withSelfRel());
		return dto;
		
	}
	
	public UserDTO update(UserDTO user) {
		if(user == null) throw new RequiredObjectIsNullException();
		var entity = userRepository.findById(user.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		entity.setFirstName(user.getFirstName());
		entity.setLastName(user.getLastName());
		entity.setEmail(user.getEmail());
		
		var dto = DozeMapper.parseObject(userRepository.save(entity), UserDTO.class);
		dto.add(linkTo(methodOn(UserController.class).findById(dto.getId())).withSelfRel());
		return dto;
	}
	
	public void delete(Long id) {
		var entity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		userRepository.delete(entity);
	}
}
