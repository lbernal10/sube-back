package com.sube.plus.apaseo.sube_back.service.impl;

import com.sube.plus.apaseo.sube_back.converter.SchoolDataMapper;
import com.sube.plus.apaseo.sube_back.model.SchoolData;
import com.sube.plus.apaseo.sube_back.model.request.SchoolDataRequestDTO;
import com.sube.plus.apaseo.sube_back.model.response.SchoolDataResponseDTO;
import com.sube.plus.apaseo.sube_back.repository.SchoolDataRepository;
import com.sube.plus.apaseo.sube_back.service.SchoolDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SchoolDataServiceImpl implements SchoolDataService {

    private final SchoolDataRepository repository;
    private final SchoolDataMapper mapper;

    @Override
    public SchoolDataResponseDTO create(SchoolDataRequestDTO dto) {
        SchoolData entity = mapper.toEntity(dto);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public List<SchoolDataResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SchoolDataResponseDTO getById(String id) {
        return mapper.toResponse(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("SchoolData not found: " + id)));
    }

    @Override
    public SchoolDataResponseDTO update(String id, SchoolDataRequestDTO dto) {
        SchoolData entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("SchoolData not found: " + id));
        mapper.updateEntity(entity, dto);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public void delete(String id) {

    }
}
