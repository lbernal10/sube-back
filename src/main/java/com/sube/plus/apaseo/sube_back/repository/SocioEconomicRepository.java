package com.sube.plus.apaseo.sube_back.repository;

import com.sube.plus.apaseo.sube_back.model.SocioEconomic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocioEconomicRepository extends MongoRepository<SocioEconomic, String> {
}