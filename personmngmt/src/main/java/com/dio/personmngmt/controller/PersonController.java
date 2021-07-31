package com.dio.personmngmt.controller;

//Ações que podem ser realizadas neste projeto.

import com.dio.personmngmt.dto.response.MessageResponseDTO;
import com.dio.personmngmt.dto.request.PersonDTO;
import com.dio.personmngmt.exception.PersonNotFoundException;
import com.dio.personmngmt.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/v1/people")
@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    private final PersonService personService;

//Para criar um usuário.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {
        return personService.createPerson(personDTO);
    }

//Para listar um usuário.
    @GetMapping
    public List<PersonDTO> listAll(){
      return personService.listAll();
    }

//Para procurar um usuário pelo id.
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundException {
        return personService.findById(id);
    }

//Para atualizar um usuário pelo id.
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody PersonDTO personDTO) throws PersonNotFoundException {
        return personService.updateById(id, personDTO);
    }

//Para deletar um usuário pelo id.
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws PersonNotFoundException {
        personService.delete(id);
    }

}
