package com.daniel.biblioteca.DTO;

import java.util.Date;

public record UserBookLoanDTO(Long userId, Long bookId, Date date) {
	
	
}
