package com.sube.plus.apaseo.sube_back.service.impl;

import com.sube.plus.apaseo.sube_back.converter.ApplicationsMapper;
import com.sube.plus.apaseo.sube_back.model.Applications;
import com.sube.plus.apaseo.sube_back.model.enums.ApplicationStatus;
import com.sube.plus.apaseo.sube_back.model.request.ApplicationsRequest;
import com.sube.plus.apaseo.sube_back.model.response.ApplicationsResponse;
import com.sube.plus.apaseo.sube_back.repository.ApplicationsRepository;
import com.sube.plus.apaseo.sube_back.service.ApplicationsService;
import com.sube.plus.apaseo.sube_back.util.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ApplicationsServiceImpl implements ApplicationsService {

    private final ApplicationsRepository applicationsRepository;
    private final ApplicationsMapper applicationsMapper;

    @Override
    public List<ApplicationsResponse> getAllApplications() {
        return applicationsRepository.findAll()
                .stream()
                .map(applicationsMapper::toApplicationsResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ApplicationsResponse getApplicationById(String id) {
        Applications applications = applicationsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Application not found with id: " + id));
        return applicationsMapper.toApplicationsResponse(applications);
    }

    @Override
    public ApplicationsResponse createApplication(ApplicationsRequest request) {
        Applications application = Applications.builder()
                .idAnnouncement(request.getIdAnnouncement())
                .socioEconomic(applicationsMapper.toSocioEconomic(request.getSocioEconomic()))
                .tutor(applicationsMapper.toTutor(request.getTutor()))
                .schoolData(applicationsMapper.toSchoolData(request.getSchoolData()))
                .userId(request.getUserId())
                .status(ApplicationStatus.UNDER_REVIEW)
                .activeSupport(request.isActiveSupport())
                .juveCardDelivered(request.isJuveCardDelivered())
                .document(request.getDocument())
                .createdAt(ZonedDateTime.now())
                .updatedAt(null)
                .folio(generateFolio())
                .build();

        Applications saved = applicationsRepository.save(application);
        return applicationsMapper.toApplicationsResponse(saved);
    }

    @Override
    public ApplicationsResponse updateApplication(String id, ApplicationsRequest request) {
        Applications existing = applicationsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Application not found with id: " + id));

        existing.setIdAnnouncement(request.getIdAnnouncement());
        existing.setSocioEconomic(applicationsMapper.toSocioEconomic(request.getSocioEconomic()));

        existing.setSocioEconomic(
                request.getSocioEconomic() != null
                        ? applicationsMapper.toSocioEconomic(request.getSocioEconomic())
                        : null
        );

        existing.setTutor(
                request.getTutor() != null
                        ? applicationsMapper.toTutor(request.getTutor())
                        : null
        );

        existing.setSchoolData(
                request.getSchoolData() != null
                        ? applicationsMapper.toSchoolData(request.getSchoolData())
                        : null
        );

        existing.setUserId(request.getUserId());
        existing.setActiveSupport(request.isActiveSupport());
        existing.setJuveCardDelivered(request.isJuveCardDelivered());
        existing.setDocument(request.getDocument());
        existing.setUpdatedAt(ZonedDateTime.now());

        Applications updated = applicationsRepository.save(existing);
        return applicationsMapper.toApplicationsResponse(updated);
    }

    @Override
    public ApplicationsResponse deleteApplication(String id) {
        Applications applications = applicationsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Application not found with id: " + id));

        applicationsRepository.delete(applications);
        return applicationsMapper.toApplicationsResponse(applications);
    }

    @Override
    public List<ApplicationsResponse> getApplicationsByUserId(String userId) {
        return applicationsRepository.findByUserId(userId)
                .stream()
                .map(applicationsMapper::toApplicationsResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ApplicationsResponse> getApplicationsByStatus(ApplicationStatus status) {
        return applicationsRepository.findByStatus(status)
                .stream()
                .map(applicationsMapper::toApplicationsResponse)
                .collect(Collectors.toList());
    }

    private String generateFolio() {
        LocalDateTime now = LocalDateTime.now();
        return "SUBE-" + now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
    }

}
