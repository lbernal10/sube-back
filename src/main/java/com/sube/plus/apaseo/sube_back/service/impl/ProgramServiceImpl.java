package com.sube.plus.apaseo.sube_back.service.impl;

import com.sube.plus.apaseo.sube_back.converter.ProgramMapper;
import com.sube.plus.apaseo.sube_back.model.Program;
import com.sube.plus.apaseo.sube_back.model.request.ProgramRequest;
import com.sube.plus.apaseo.sube_back.model.response.ProgramResponse;
import com.sube.plus.apaseo.sube_back.repository.ProgramRepository;
import com.sube.plus.apaseo.sube_back.service.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepository programRepository;
    private final ProgramMapper programMapper;

    @Override
    public ProgramResponse createProgram(ProgramRequest programRequest) {
        Program programSave = programRepository.save(programMapper.toProgram(programRequest));
        return programMapper.toProgramResponse(programSave);
    }

    @Override
    public List<Program> getAllPrograms() {
        return programRepository.findAll();
    }

    @Override
    public Program getProgramById(String id) {
        return programRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Program not found with ID: " + id));
    }

    @Override
    public Program updateProgram(String id, Program program) {
        Program existingProgram = getProgramById(id);
        program.setId(existingProgram.getId());
        return programRepository.save(program);
    }

    @Override
    public void deleteProgram(String id) {
        Program program = getProgramById(id);
        programRepository.delete(program);
    }
}
