package com.dio.personmngmt.service;

//Teste para as ações da classe PersonService.

import com.dio.personmngmt.dto.request.PersonDTO;
import com.dio.personmngmt.dto.response.MessageResponseDTO;
import com.dio.personmngmt.entity.Person;
import com.dio.personmngmt.exception.PersonNotFoundException;
import com.dio.personmngmt.mapper.PersonMapper;
import com.dio.personmngmt.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.dio.personmngmt.utils.PersonUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;
    
    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonMapper personMapper;

//Teste adição de usuário.
    @Test
    void testAddPerson() {
        PersonDTO personDTO = createFakeDTO();
        Person expectedSavedPerson = createFakeEntity();

        when(personMapper.toModel(personDTO)).thenReturn(expectedSavedPerson);
        when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);


    MessageResponseDTO successMessage = personService.createPerson(personDTO);

    assertEquals("Created person with id 1", successMessage.getMessage());
}

//Teste deletar usuário por id válido.
    @Test
    void testDeleteValitPersonId() throws PersonNotFoundException {
        Long deletedPersonId = 1L;

        Person expectedPersonToDelete = createFakeEntity();

        when(personRepository.findById(deletedPersonId)).thenReturn(Optional.of(expectedPersonToDelete));
        MessageResponseDTO expectecSuccessMessage = deleteExpectedMessageResponse(expectedPersonToDelete.getId());
        personService.delete(deletedPersonId);

        verify(personRepository, times(1)).deleteById(deletedPersonId);
    }

    private MessageResponseDTO deleteExpectedMessageResponse(Long id) {
        return MessageResponseDTO.builder()
                .message("Delete person with ID" + id)
                .build();
    }

//Teste deletar usuário por id inválido.
    @Test
    void testDeleteInvalidPersonId() throws PersonNotFoundException {

        Long invalidPersonId = 1L;

        when(personRepository.findById(invalidPersonId))
                .thenReturn(Optional.ofNullable(any(Person.class)));

        assertThrows(PersonNotFoundException.class, () -> personService.delete(invalidPersonId));
    }

//Teste encontrar usuário por id válido.
    @Test
    void testFindPersonValidId() throws PersonNotFoundException {
        PersonDTO expectedPersonDTO = createFakeDTO();
        Person expectedSavedPerson = createFakeEntity();
        expectedPersonDTO.setId(expectedSavedPerson.getId());

        when(personRepository.findById(expectedSavedPerson.getId())).thenReturn(Optional.of(expectedSavedPerson));

        when(personMapper.toDTO(expectedSavedPerson)).thenReturn(expectedPersonDTO);

        PersonDTO personDTO = personService.findById(expectedSavedPerson.getId());

        assertEquals(expectedPersonDTO, personDTO);

        assertEquals(expectedSavedPerson.getId(), personDTO.getId());
        assertEquals(expectedSavedPerson.getFirstName(), personDTO.getFirstName());
    }

//Teste encontrar usuário por id inválido.
    @Test
    void testFindPersonInvalidId() {
        Long invalidPersonId = 1L;
        when(personRepository.findById(invalidPersonId))
                .thenReturn(Optional.ofNullable(any(Person.class)));

        assertThrows(PersonNotFoundException.class, () -> personService.findById(invalidPersonId));
    }

//Teste update usuário com id válido.
    @Test
    void testUpdateValidId() throws PersonNotFoundException {
        Long updatedPersonId = 2L;

        PersonDTO updatePersonDTORequest = createFakeDTO();
        updatePersonDTORequest.setId(updatedPersonId);
        updatePersonDTORequest.setLastName("Muzetti");

        Person expectedPersonToUpdate = createFakeEntity();
        expectedPersonToUpdate.setId(updatedPersonId);

        Person expectedPersonUpdated = createFakeEntity();
        expectedPersonUpdated.setId(updatedPersonId);
        expectedPersonToUpdate.setLastName(updatePersonDTORequest.getLastName());

        when(personRepository.findById(updatedPersonId)).thenReturn(Optional.of(expectedPersonUpdated));
        when(personMapper.toModel(updatePersonDTORequest)).thenReturn(expectedPersonUpdated);
        when(personRepository.save(any(Person.class))).thenReturn(expectedPersonUpdated);

        MessageResponseDTO successMessage = personService.updateById(updatedPersonId, updatePersonDTORequest);

        assertEquals("Updated person with id 2", successMessage.getMessage());
    }

//Teste update usuário id inválido.
    @Test
    void testUpdateInvalidId() throws PersonNotFoundException {
        var invalidPersonId = 1L;

        PersonDTO updatePersonDTORequest = createFakeDTO();
        updatePersonDTORequest.setId(invalidPersonId);
        updatePersonDTORequest.setLastName("Muzetti");

        when(personRepository.findById(invalidPersonId))
                .thenReturn(Optional.ofNullable(any(Person.class)));

        assertThrows(PersonNotFoundException.class, () -> personService.updateById(invalidPersonId, updatePersonDTORequest));
    }

//Teste listar usuários.
    @Test
    void testListAllPeople() {
        List<Person> expectedRegisteredPeople = Collections.singletonList(createFakeEntity());
        PersonDTO personDTO = createFakeDTO();

        when(personRepository.findAll()).thenReturn(expectedRegisteredPeople);
        when(personMapper.toDTO(any(Person.class))).thenReturn(personDTO);

        List<PersonDTO> expectedPeopleDTOList = personService.listAll();

        assertFalse(expectedPeopleDTOList.isEmpty());
        assertEquals(expectedPeopleDTOList.get(0).getId(), personDTO.getId());
    }

}
