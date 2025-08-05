package com.sube.plus.apaseo.sube_back.converter;

import com.sube.plus.apaseo.sube_back.model.Tutor;
import com.sube.plus.apaseo.sube_back.model.request.TutorRequestDTO;
import com.sube.plus.apaseo.sube_back.model.response.TutorResponseDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TutorMapper {

    @Mapping(target = "id", ignore = true) // El ID se ignora al crear
    Tutor toEntity(TutorRequestDTO request);

    TutorResponseDTO toResponse(Tutor tutor);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget Tutor entity, TutorRequestDTO dto);
}