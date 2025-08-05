package com.sube.plus.apaseo.sube_back.converter;

import com.sube.plus.apaseo.sube_back.model.SocioEconomic;
import com.sube.plus.apaseo.sube_back.model.request.SocioEconomicRequestDTO;
import com.sube.plus.apaseo.sube_back.model.response.SocioEconomicResponseDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SocioEconomicMapper {

    @Mapping(target = "id", ignore = true)
    SocioEconomic toEntity(SocioEconomicRequestDTO dto);

    SocioEconomicResponseDTO toResponse(SocioEconomic entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget SocioEconomic entity, SocioEconomicRequestDTO dto);
}
