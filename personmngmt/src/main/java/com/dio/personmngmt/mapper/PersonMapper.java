package com.dio.personmngmt.mapper;

//Para conversão de variáveis entre classes.

import com.dio.personmngmt.dto.request.PersonDTO;
import com.dio.personmngmt.entity.Person;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy") //conversão entre a variável que é diferente entre Person e PersonDTO.
    Person toModel(PersonDTO personDTO);

    PersonDTO toDTO(Person person);
}
