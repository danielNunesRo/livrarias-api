package com.daniel.biblioteca.unittests.mappers.customs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.daniel.biblioteca.DTO.UserDTO;
import com.daniel.biblioteca.mappers.custom.UserMapper;
import com.daniel.biblioteca.models.User;

public class UserMapperTest {
	
	private final UserMapper userMapper = new UserMapper();
	
	@Test
	public void testConvertEntityToDto() {
		User user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");

        UserDTO dto = userMapper.convertEntityToDto(user);

        assertEquals(user.getId(), dto.getId());
        assertEquals(user.getFirstName(), dto.getFirstName());
        assertEquals(user.getLastName(), dto.getLastName());
        assertEquals(user.getEmail(), dto.getEmail());
	}
	
	 @Test
	    public void testConvertDtoToEntity() {
	        UserDTO dto = new UserDTO(1L, "John", "Doe", "john.doe@example.com");

	        User user = userMapper.convertDtoToEntity(dto);

	        assertEquals(dto.getId(), user.getId());
	        assertEquals(dto.getFirstName(), user.getFirstName());
	        assertEquals(dto.getLastName(), user.getLastName());
	        assertEquals(dto.getEmail(), user.getEmail());
	    }
	
}
