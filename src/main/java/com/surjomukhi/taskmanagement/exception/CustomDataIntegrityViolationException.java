package com.surjomukhi.taskmanagement.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CustomDataIntegrityViolationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CustomDataIntegrityViolationException(String string) {
        super(string);
    }
}
