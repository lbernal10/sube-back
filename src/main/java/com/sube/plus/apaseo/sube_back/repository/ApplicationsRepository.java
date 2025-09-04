package com.sube.plus.apaseo.sube_back.repository;

import com.sube.plus.apaseo.sube_back.model.Applications;
import com.sube.plus.apaseo.sube_back.model.enums.ApplicationStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationsRepository extends MongoRepository<Applications, String> {

    List<Applications> findByUserId(String userId);

    List<Applications> findByStatus(ApplicationStatus status);

}