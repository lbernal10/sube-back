package com.sube.plus.apaseo.sube_back.service;

import com.sube.plus.apaseo.sube_back.model.request.SchoolDataRequestDTO;
import com.sube.plus.apaseo.sube_back.model.response.SchoolDataResponseDTO;

import java.util.List;

public interface SchoolDataService {

    SchoolDataResponseDTO create(SchoolDataRequestDTO dto);

    List<SchoolDataResponseDTO> getAll();

    SchoolDataResponseDTO getById(String id);

    SchoolDataResponseDTO update(String id, SchoolDataRequestDTO dto);

    void delete(String id);

}
