package com.dio.personmngmt.utils;

import com.dio.personmngmt.dto.request.PersonDTO;
import com.dio.personmngmt.entity.Person;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDate;
import java.util.Collections;

public class PersonUtils {

//Variáveis do usuário.
    private static final String FIRST_NAME = "Maria";
    private static final String LAST_NAME = "Ribeiro";
    private static final String CPF_NUMBER = "368.932.318-57";
    private static final String GENDER = "Feminino";
    private static final long PERSON_ID = 1L;
    public static final LocalDate BIRTH_DATE = LocalDate.of(1989,8,04);

//Variáveis PersonDTO.
    public static PersonDTO createFakeDTO(){
        return PersonDTO.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF_NUMBER)
                .gender(GENDER)
                .birthDate("04-08-1989")
                .phones(Collections.singletonList(PhonesUtils.createFakeDTO()))
                .build();
    }

//Variáveis Person.
    public static Person createFakeEntity(){
        return Person.builder()
                .id(PERSON_ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF_NUMBER)
                .gender(GENDER)
                .birthDate(BIRTH_DATE)
                .phones(Collections.singletonList(PhonesUtils.createFakeEntity()))
                .build();
    }


    public static String asJsonString(PersonDTO personDTO) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.registerModules(new JavaTimeModule());

            return objectMapper.writeValueAsString(personDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
