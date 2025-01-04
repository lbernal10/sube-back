package com.sube.plus.apaseo.sube_back.service;

import com.sube.plus.apaseo.sube_back.model.Program;
import com.sube.plus.apaseo.sube_back.model.request.ProgramRequest;
import com.sube.plus.apaseo.sube_back.model.response.ProgramResponse;

import java.util.List;

public interface ProgramService {

    ProgramResponse createProgram(ProgramRequest programRequest);

    List<Program> getAllPrograms();

    Program getProgramById(String id);

    Program updateProgram(String id, Program program);

    void deleteProgram(String id);

}
