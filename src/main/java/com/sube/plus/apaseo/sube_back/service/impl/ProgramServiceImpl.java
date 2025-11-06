package com.sube.plus.apaseo.sube_back.service.impl;

import com.sube.plus.apaseo.sube_back.converter.ProgramMapper;
import com.sube.plus.apaseo.sube_back.model.DocumentProgram;
import com.sube.plus.apaseo.sube_back.model.Program;
import com.sube.plus.apaseo.sube_back.model.enums.ProgramStatus;
import com.sube.plus.apaseo.sube_back.model.request.DocumentProgramUpdateRequest;
import com.sube.plus.apaseo.sube_back.model.request.ProgramRequest;
import com.sube.plus.apaseo.sube_back.model.request.ProgramUpdatedRequest;
import com.sube.plus.apaseo.sube_back.model.response.ProgramResponse;
import com.sube.plus.apaseo.sube_back.repository.ProgramRepository;
import com.sube.plus.apaseo.sube_back.service.ProgramService;
import com.sube.plus.apaseo.sube_back.util.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepository programRepository;
    private final ProgramMapper programMapper;

    @Override
    public ProgramResponse createProgram(ProgramRequest programRequest) {
        programRequest.setProgramStatus(ProgramStatus.ACTIVE);

        // Zona fija sin horario de verano (UTC-6)
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Etc/GMT+6"));

        System.out.println("ZonedDateTime: " + now);
        System.out.println("Offset: " + now.getOffset());

        programRequest.setCreatedAt(now);

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
    public ProgramResponse updateProgram(String id, ProgramUpdatedRequest programRequest) {
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
        existingProgram.setUpdatedAt(ZonedDateTime.now());

        if (programRequest.getDocument() != null && !programRequest.getDocument().isEmpty()) {
            List<DocumentProgram> existingDocs = new ArrayList<>(existingProgram.getDocument());

            for (DocumentProgramUpdateRequest docReq : programRequest.getDocument()) {
                // Solo procesar si el documento tiene ID
                if (docReq.getId() != null) {
                    Optional<DocumentProgram> existingDocOpt = existingDocs.stream()
                            .filter(d -> d.getId().equals(docReq.getId()))
                            .findFirst();

                    if (existingDocOpt.isPresent()) {
                        DocumentProgram existingDoc = existingDocOpt.get();

                        // 🔹 Actualizar solo los campos enviados
                        if (docReq.getName() != null) existingDoc.setName(docReq.getName());
                        if (docReq.getDescription() != null) existingDoc.setDescription(docReq.getDescription());
                        if (docReq.getDocumentType() != null) existingDoc.setDocumentType(docReq.getDocumentType());
                        if (docReq.getRequireTemplate() != null) existingDoc.setRequireTemplate(docReq.getRequireTemplate());
                        if (docReq.getTemplateId() != null) existingDoc.setTemplateId(docReq.getTemplateId());
                        if (docReq.getTypeDocumentProgram() != null) existingDoc.setTypeDocumentProgram(docReq.getTypeDocumentProgram());
                        if (docReq.getProgramDocumentStatus() != null) existingDoc.setProgramDocumentStatus(docReq.getProgramDocumentStatus());

                        // 🔹 Actualizar fecha de modificación
                        existingDoc.setUpdatedAt(ZonedDateTime.now());
                    }
                    // ⚠️ Si el ID no se encuentra, no se hace nada
                }
                // ⚠️ Si el documento no tiene ID, se ignora completamente (no se agrega)
            }

            // 🔹 Mantener todos los documentos existentes más los actualizados/nuevos
            existingProgram.setDocument(existingDocs);
        }

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
