package com.sube.plus.apaseo.sube_back.converter;

import com.sube.plus.apaseo.model.Address;
import com.sube.plus.apaseo.model.Person;
import com.sube.plus.apaseo.model.request.AddressRequest;
import com.sube.plus.apaseo.model.request.PersonRequest;
import com.sube.plus.apaseo.model.response.AddressResponse;
import com.sube.plus.apaseo.model.response.PersonResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring") // Define el componente como un Bean Spring
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "id", ignore = true) // El ID se ignora, ya que es generado
    Person toPerson(PersonRequest personRequest);

    PersonResponse toPersonResponse(Person person);

    AddressResponse map(Address address);

    Address toAddress(AddressRequest address);
}
