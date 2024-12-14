package com.sube.plus.apaseo.sube_back.service.impl;

import com.sube.plus.apaseo.sube_back.converter.PersonMapper;
import com.sube.plus.apaseo.sube_back.model.Person;
import com.sube.plus.apaseo.sube_back.model.request.PersonRequest;
import com.sube.plus.apaseo.sube_back.model.response.PersonResponse;
import com.sube.plus.apaseo.sube_back.repository.PersonRepository;
import com.sube.plus.apaseo.sube_back.service.PersonService;
import com.sube.plus.apaseo.sube_back.util.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Override
    public PersonResponse createPerson(PersonRequest personRequest) {
        Person person = personMapper.toPerson(personRequest);

        Person personSave = personRepository.save(person);

        return personMapper.toPersonResponse(personSave);
    }

    @Override
    public PersonResponse updatePerson(String id, PersonRequest personRequest) {
        Person existingPerson = personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Person not found with id: " + id));

        // Update fields
        existingPerson.setName(personRequest.getName());
        existingPerson.setLastNameP(personRequest.getLastNameP());
        existingPerson.setLastNameM(personRequest.getLastNameM());
        existingPerson.setDateOfBirth(personRequest.getDateOfBirth());
        existingPerson.setAddress(personMapper.toAddress(personRequest.getAddress()));
        existingPerson.setCurp(personRequest.getCurp());
        existingPerson.setRfc(personRequest.getRfc());
        existingPerson.setGender(personRequest.getGender());
        existingPerson.setDisability(personRequest.getDisability());
        existingPerson.setMaritalStatus(personRequest.getMaritalStatus());
        existingPerson.setIndigenousGroup(personRequest.getIndigenousGroup());
        existingPerson.setMotherOrPregnent(personRequest.getMotherOrPregnent());
        existingPerson.setInmigrationStatus(personRequest.getInmigrationStatus());
        existingPerson.setAddressHome(personMapper.toAddress(personRequest.getAddressHome()));
        existingPerson.setSettlementType(personRequest.getSettlementType());
        existingPerson.setRoadType(personRequest.getRoadType());
        existingPerson.setPathType(personRequest.getPathType());

        Person updatedPerson = personRepository.save(existingPerson);

        return personMapper.toPersonResponse(updatedPerson);
    }

    @Override
    public PersonResponse getPersonById(String id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Person not found with id: " + id));

        return personMapper.toPersonResponse(person);
    }
}
