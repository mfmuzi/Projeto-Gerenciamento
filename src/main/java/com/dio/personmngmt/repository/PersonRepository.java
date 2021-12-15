package com.dio.personmngmt.repository;


import com.dio.personmngmt.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
