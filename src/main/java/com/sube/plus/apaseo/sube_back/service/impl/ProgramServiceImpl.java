package com.sube.plus.apaseo.sube_back.service.impl;

import com.sube.plus.apaseo.sube_back.converter.ProgramMapper;
import com.sube.plus.apaseo.sube_back.model.Program;
import com.sube.plus.apaseo.sube_back.model.enums.ProgramStatus;
import com.sube.plus.apaseo.sube_back.model.enums.TemplateStatus;
import com.sube.plus.apaseo.sube_back.model.request.ProgramRequest;
import com.sube.plus.apaseo.sube_back.model.response.ProgramResponse;
import com.sube.plus.apaseo.sube_back.repository.ProgramRepository;
import com.sube.plus.apaseo.sube_back.service.ProgramService;
import com.sube.plus.apaseo.sube_back.util.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepository programRepository;
    private final ProgramMapper programMapper;

    @Override
    public ProgramResponse createProgram(ProgramRequest programRequest) {
        programRequest.setProgramStatus(ProgramStatus.ACTIVE);
        Program programSave = programRepository.save(programMapper.toProgram(programRequest));
        return programMapper.toProgramResponse(programSave);
    }

    @Override
    public List<ProgramResponse> getAllPrograms() {
        List<Program> programs = programRepository.findAll();

        return programs.stream()
                .filter(program -> ProgramStatus.ACTIVE.equals(program.getProgramStatus()))  // Filtra los programas con estado ACTIVE
                .map(programMapper::toProgramResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProgramResponse getProgramById(String id) {
        Program program = programRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Program not found with ID: " + id));

        return programMapper.toProgramResponse(program);
    }

    @Override
    public ProgramResponse updateProgram(String id, ProgramRequest programRequest) {
        Program existingProgram = programRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Program not found with id: " + id));

        // Update fields
        existingProgram.setName(programRequest.getName());
        existingProgram.setDescription(programRequest.getDescription());
        existingProgram.setSupportType(programRequest.getSupportType());
        existingProgram.setRequireEvidence(programRequest.getRequireEvidence());
        existingProgram.setSocioEconomicStudy(programRequest.getSocioEconomicStudy());
        existingProgram.setCompatibilityWithOtherPrograms(programRequest.getCompatibilityWithOtherPrograms());
        existingProgram.setJuveCard(programRequest.getJuveCard());
        existingProgram.setProgramStatus(programRequest.getProgramStatus());
        existingProgram.setDocument(programMapper.toDocumentProgramList(programRequest.getDocument()));

        Program updatedProgram = programRepository.save(existingProgram);

        return programMapper.toProgramResponse(updatedProgram);
    }

    @Override
    public ProgramResponse deleteProgram(String id) {
        Program existingProgram = programRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Program not found with id: " + id));

        existingProgram.setProgramStatus(ProgramStatus.INACTIVE);

        Program updatedProgram = programRepository.save(existingProgram);

        return programMapper.toProgramResponse(updatedProgram);
    }
}
