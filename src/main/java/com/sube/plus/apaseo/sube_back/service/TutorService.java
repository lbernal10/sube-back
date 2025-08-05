package com.sube.plus.apaseo.sube_back.service;

import com.sube.plus.apaseo.sube_back.model.request.TutorRequestDTO;
import com.sube.plus.apaseo.sube_back.model.response.TutorResponseDTO;

import java.util.List;

public interface TutorService {

    TutorResponseDTO createTutor(TutorRequestDTO request);

    List<TutorResponseDTO> getAllTutors();

    TutorResponseDTO getTutorById(String id);

    TutorResponseDTO updateTutor(String id, TutorRequestDTO request);

    void deleteTutor(String id);

}
