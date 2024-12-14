package com.sube.plus.apaseo.sube_back.repository;

import com.sube.plus.apaseo.sube_back.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends MongoRepository<Person, String> {

    Optional<Person> findById(String Id);


}