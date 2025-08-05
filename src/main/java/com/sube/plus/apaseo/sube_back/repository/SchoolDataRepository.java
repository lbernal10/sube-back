package com.sube.plus.apaseo.sube_back.repository;


import com.sube.plus.apaseo.sube_back.model.SchoolData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolDataRepository extends MongoRepository<SchoolData, String> {

}