package com.sube.plus.apaseo.sube_back.converter;

import com.sube.plus.apaseo.sube_back.model.SchoolData;
import com.sube.plus.apaseo.sube_back.model.request.SchoolDataRequestDTO;
import com.sube.plus.apaseo.sube_back.model.response.SchoolDataResponseDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SchoolDataMapper {

    @Mapping(target = "id", ignore = true)
    SchoolData toEntity(SchoolDataRequestDTO dto);

    SchoolDataResponseDTO toResponse(SchoolData entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget SchoolData entity, SchoolDataRequestDTO dto);

}