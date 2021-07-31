package com.dio.personmngmt.repository;

//Implementação da interface JPA (facilidade de métodos CRUD - create, read, update, delete) no repositório.

import com.dio.personmngmt.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
