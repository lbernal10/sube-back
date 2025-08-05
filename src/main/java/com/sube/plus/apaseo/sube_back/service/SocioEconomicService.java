package com.sube.plus.apaseo.sube_back.service;

import com.sube.plus.apaseo.sube_back.model.request.SocioEconomicRequestDTO;
import com.sube.plus.apaseo.sube_back.model.response.SocioEconomicResponseDTO;

import java.util.List;

public interface SocioEconomicService {

    SocioEconomicResponseDTO create(SocioEconomicRequestDTO dto);

    List<SocioEconomicResponseDTO> getAll();

    SocioEconomicResponseDTO getById(String id);

    SocioEconomicResponseDTO update(String id, SocioEconomicRequestDTO dto);

    void delete(String id);

}
