package com.sube.plus.apaseo.sube_back.converter;

import com.sube.plus.apaseo.sube_back.model.DocumentProgram;
import com.sube.plus.apaseo.sube_back.model.Program;
import com.sube.plus.apaseo.sube_back.model.request.DocumentProgramRequest;
import com.sube.plus.apaseo.sube_back.model.request.DocumentProgramUpdateRequest;
import com.sube.plus.apaseo.sube_back.model.request.ProgramRequest;
import com.sube.plus.apaseo.sube_back.model.request.ProgramUpdatedRequest;
import com.sube.plus.apaseo.sube_back.model.response.ProgramResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DateMapper.class})
public interface ProgramMapper {

    ProgramMapper INSTANCE = Mappers.getMapper(ProgramMapper.class);

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "localToZoned")
    ProgramResponse toProgramResponse(Program program);

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "zonedToLocal")
    Program toProgram(ProgramRequest programRequest);

    List<DocumentProgram> toDocumentProgramList(List<DocumentProgramRequest> documentRequests);

     DocumentProgram toDocumentProgram(DocumentProgramUpdateRequest documentProgramRequest);

}
