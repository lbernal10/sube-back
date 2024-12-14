package com.sube.plus.apaseo.sube_back.converter;

import com.sube.plus.apaseo.model.User;
import com.sube.plus.apaseo.model.request.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring") // Define el componente como un Bean Spring
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true) // El ID se ignora, ya que es generado
    User toUser(UserRequest userRequest);

}
