package com.sube.plus.apaseo.sube_back.converter;

import com.sube.plus.apaseo.sube_back.model.Applications;
import com.sube.plus.apaseo.sube_back.model.SchoolData;
import com.sube.plus.apaseo.sube_back.model.SocioEconomic;
import com.sube.plus.apaseo.sube_back.model.Tutor;
import com.sube.plus.apaseo.sube_back.model.request.ApplicationsRequest;
import com.sube.plus.apaseo.sube_back.model.request.SchoolDataRequest;
import com.sube.plus.apaseo.sube_back.model.request.SocioEconomicRequest;
import com.sube.plus.apaseo.sube_back.model.request.TutorRequest;
import com.sube.plus.apaseo.sube_back.model.response.ApplicationsResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ApplicationsMapper {

    ApplicationsMapper INSTANCE = Mappers.getMapper(ApplicationsMapper.class);

    ApplicationsResponse toApplicationsResponse(Applications applications);

    Applications toApplications(ApplicationsRequest applicationsRequest);

    SocioEconomic toSocioEconomic(SocioEconomicRequest request);

    Tutor toTutor(TutorRequest request);

    SchoolData toSchoolData(SchoolDataRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSocioEconomicFromRequest(SocioEconomicRequest request, @MappingTarget SocioEconomic entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTutorFromRequest(TutorRequest request, @MappingTarget Tutor entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSchoolDataFromRequest(SchoolDataRequest request, @MappingTarget SchoolData entity);

}