package com.dio.personmngmt.exception;

//Exceção criada para quando o usuário não é encontrado pelo id.

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends Exception {

    public PersonNotFoundException(Long id) {
        super(String.format("Person not found with id: ", id));
    }
}
