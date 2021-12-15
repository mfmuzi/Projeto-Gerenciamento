package com.dio.personmngmt.service;

//O que cada ação deve fazer dentro do projeto.

import com.dio.personmngmt.exception.PersonNotFoundException;
import lombok.AllArgsConstructor;
import com.dio.personmngmt.dto.request.PersonDTO;
import com.dio.personmngmt.dto.response.MessageResponseDTO;
import com.dio.personmngmt.entity.Person;
import com.dio.personmngmt.mapper.PersonMapper;
import com.dio.personmngmt.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;


//Criar um objeto do tipo pessoa. OK
    public MessageResponseDTO createPerson(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(personToSave);

        return createMessageResponse("Created person with id ",savedPerson.getId());
    }


//Lista todas as pessoas. OK
    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

//Retorna uma pessoa com id válido ou uma exceção.OK
    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = personRepository.findById(id)
                .orElseThrow(() ->new PersonNotFoundException(id));
        return personMapper.toDTO(person);
    }

//Deleta uma pessoa com id válido ou retorna uma exceção. OK
    public void delete(Long id) throws PersonNotFoundException {
        personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        personRepository.deleteById(id);
    }

//Atualiza uma pessoa com id válido ou retorna uma exceção.OK
    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
        
        Person personToUpdate = personMapper.toModel(personDTO);
        Person updatedPerson = personRepository.save(personToUpdate);

        return createMessageResponse("Updated person with id ", updatedPerson.getId());
    }


//Como deve ser a mensagem de retorno nas operações. OK
    private MessageResponseDTO createMessageResponse(String message, Long id) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }

}
