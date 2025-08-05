package com.sube.plus.apaseo.sube_back.service.impl;

import com.sube.plus.apaseo.sube_back.converter.TutorMapper;
import com.sube.plus.apaseo.sube_back.model.Tutor;
import com.sube.plus.apaseo.sube_back.model.request.TutorRequestDTO;
import com.sube.plus.apaseo.sube_back.model.response.TutorResponseDTO;
import com.sube.plus.apaseo.sube_back.repository.TutorRepository;
import com.sube.plus.apaseo.sube_back.service.TutorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TutorServiceImpl implements TutorService {
    private final TutorRepository tutorRepository;
    private final TutorMapper tutorMapper;

    @Override
    public TutorResponseDTO createTutor(TutorRequestDTO request) {
        Tutor tutor = tutorMapper.toEntity(request);
        return tutorMapper.toResponse(tutorRepository.save(tutor));
    }

    @Override
    public List<TutorResponseDTO> getAllTutors() {
        return tutorRepository.findAll()
                .stream()
                .map(tutorMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TutorResponseDTO getTutorById(String id) {
        Tutor tutor = tutorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tutor not found with id: " + id));
        return tutorMapper.toResponse(tutor);
    }

    @Override
    public TutorResponseDTO updateTutor(String id, TutorRequestDTO request) {
        Tutor tutor = tutorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tutor not found with id: " + id));
        tutorMapper.updateEntity(tutor, request);
        return tutorMapper.toResponse(tutorRepository.save(tutor));
    }

    @Override
    public void deleteTutor(String id) {

    }
}
