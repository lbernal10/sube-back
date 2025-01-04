package com.sube.plus.apaseo.sube_back.converter;

import com.sube.plus.apaseo.sube_back.model.Template;
import com.sube.plus.apaseo.sube_back.model.response.TemplateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring") // Define el componente como un Bean Spring
public interface TemplateMapper {

    TemplateMapper INSTANCE = Mappers.getMapper(TemplateMapper.class);

    TemplateResponse toTemplateResponse(Template template);

}
