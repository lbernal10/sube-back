package com.sube.plus.apaseo.sube_back.repository;

import com.sube.plus.apaseo.sube_back.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<User> findByIdAndEmail(String id, String email);

    List<User> findByStatusAndType(String status, String type);

}