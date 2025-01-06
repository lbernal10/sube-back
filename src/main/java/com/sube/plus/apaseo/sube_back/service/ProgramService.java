package com.sube.plus.apaseo.sube_back.service;

import com.sube.plus.apaseo.sube_back.model.request.ProgramRequest;
import com.sube.plus.apaseo.sube_back.model.response.ProgramResponse;

import java.util.List;

public interface ProgramService {

    ProgramResponse createProgram(ProgramRequest programRequest);

    List<ProgramResponse> getAllPrograms();

    ProgramResponse getProgramById(String id);

    ProgramResponse updateProgram(String id, ProgramRequest programRequest);

    ProgramResponse deleteProgram(String id);

}
