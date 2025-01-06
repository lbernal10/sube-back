package com.sube.plus.apaseo.sube_back.converter;

import com.sube.plus.apaseo.sube_back.model.DocumentProgram;
import com.sube.plus.apaseo.sube_back.model.Program;
import com.sube.plus.apaseo.sube_back.model.request.DocumentProgramRequest;
import com.sube.plus.apaseo.sube_back.model.request.ProgramRequest;
import com.sube.plus.apaseo.sube_back.model.response.ProgramResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring") // Define el componente como un Bean Spring
public interface ProgramMapper {

    ProgramMapper INSTANCE = Mappers.getMapper(ProgramMapper.class);

    ProgramResponse toProgramResponse(Program program);

    Program toProgram(ProgramRequest programRequest);

    List<DocumentProgram> toDocumentProgramList(List<DocumentProgramRequest> documentRequests);
}
