package com.sube.plus.apaseo.sube_back.service.impl;

import com.sube.plus.apaseo.sube_back.converter.SocioEconomicMapper;
import com.sube.plus.apaseo.sube_back.model.SocioEconomic;
import com.sube.plus.apaseo.sube_back.model.request.SocioEconomicRequestDTO;
import com.sube.plus.apaseo.sube_back.model.response.SocioEconomicResponseDTO;
import com.sube.plus.apaseo.sube_back.repository.SocioEconomicRepository;
import com.sube.plus.apaseo.sube_back.service.SocioEconomicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SocioEconomicServiceImpl implements SocioEconomicService {

    private final SocioEconomicRepository repository;
    private final SocioEconomicMapper mapper;

    @Override
    public SocioEconomicResponseDTO create(SocioEconomicRequestDTO dto) {
        SocioEconomic socioEconomic = mapper.toEntity(dto);
        return mapper.toResponse(repository.save(socioEconomic));
    }

    @Override
    public List<SocioEconomicResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SocioEconomicResponseDTO getById(String id) {
        SocioEconomic socioEconomic = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("SocioEconomic not found with id: " + id));
        return mapper.toResponse(socioEconomic);
    }

    @Override
    public SocioEconomicResponseDTO update(String id, SocioEconomicRequestDTO dto) {
        SocioEconomic socioEconomic = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("SocioEconomic not found with id: " + id));
        mapper.updateEntity(socioEconomic, dto);
        return mapper.toResponse(repository.save(socioEconomic));
    }

    @Override
    public void delete(String id) {

    }
}
