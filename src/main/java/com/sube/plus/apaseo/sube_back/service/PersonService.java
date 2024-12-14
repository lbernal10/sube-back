
package com.sube.plus.apaseo.sube_back.service;

import com.sube.plus.apaseo.model.request.PersonRequest;
import com.sube.plus.apaseo.model.response.PersonResponse;

public interface PersonService {

    PersonResponse createPerson(PersonRequest personRequest);

    PersonResponse updatePerson(String id, PersonRequest personRequest);

    PersonResponse getPersonById(String id);

}