package com.sube.plus.apaseo.sube_back.repository;


import com.sube.plus.apaseo.sube_back.model.Template;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepository extends MongoRepository<Template, String> {
}