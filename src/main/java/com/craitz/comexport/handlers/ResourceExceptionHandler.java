package com.craitz.comexport.handlers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.craitz.comexport.domains.ErrorDetails;
import com.craitz.comexport.services.exceptions.JournalEntryNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(JournalEntryNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleJournalEntryNotFoundException(JournalEntryNotFoundException ex, HttpServletRequest request) {	
		ErrorDetails error = new ErrorDetails();
		error.setStatus(404L);
		error.setTitle("Object not found");
		error.setMessage("The journal entry could not be found in the database");
		error.setTimestamp(System.currentTimeMillis());
				
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorDetails> handleRuntimeException(RuntimeException ex, HttpServletRequest request) {	
		ErrorDetails error = new ErrorDetails();
		error.setStatus(500L);
		error.setTitle("Server error");
		error.setMessage(ex.getMessage());
		error.setTimestamp(System.currentTimeMillis());
				
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}
}