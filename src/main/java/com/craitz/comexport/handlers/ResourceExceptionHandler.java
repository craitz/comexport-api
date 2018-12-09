package com.craitz.comexport.handlers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.craitz.comexport.domains.ErrorDetails;
import com.craitz.comexport.exceptions.InvalidDateException;
import com.craitz.comexport.exceptions.JournalEntryNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(JournalEntryNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleJournalEntryNotFoundException(JournalEntryNotFoundException ex, HttpServletRequest request) {	
		ErrorDetails error = new ErrorDetails();
		error.setStatus(404L);
		error.setTitle("Not Found");
		error.setMessage("The journal entry could not be found in the database");
		error.setTimestamp(System.currentTimeMillis());
				
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorDetails> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, HttpServletRequest request) {	
		ErrorDetails error = new ErrorDetails();
		error.setStatus(400L);
		error.setTitle("Bad Request");
		error.setMessage("Erro na requisição");
		error.setCause(ex.getMessage());
		error.setTimestamp(System.currentTimeMillis());
				
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetails> handleMethodArgumentNotValidExceptionn(MethodArgumentNotValidException ex, HttpServletRequest request) {	
		ErrorDetails error = new ErrorDetails();
		error.setStatus(400L);
		error.setTitle("Bad Request");
		error.setMessage("Parâmetro obigatório não encontrado");
		error.setCause(ex.getMessage());
		error.setTimestamp(System.currentTimeMillis());
				
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(InvalidDateException.class)
	public ResponseEntity<ErrorDetails> handleInvalidDateException(InvalidDateException ex, HttpServletRequest request) {	
		ErrorDetails error = new ErrorDetails();
		error.setStatus(400L);
		error.setTitle("Bad Request");
		error.setMessage("Data inválida. O formato deve ser YYYYMMDD");
		error.setCause(ex.getMessage());
		error.setTimestamp(System.currentTimeMillis());
				
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<ErrorDetails> handleNumberFormatException(NumberFormatException ex, HttpServletRequest request) {	
		ErrorDetails error = new ErrorDetails();
		error.setStatus(400L);
		error.setTitle("Bad Request");
		error.setMessage("Parâmetro com formato inválido");
		error.setCause(ex.getMessage());
		error.setTimestamp(System.currentTimeMillis());
				
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}	
}