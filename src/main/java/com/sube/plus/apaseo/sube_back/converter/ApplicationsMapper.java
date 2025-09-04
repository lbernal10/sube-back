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
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ApplicationsMapper {

    ApplicationsMapper INSTANCE = Mappers.getMapper(ApplicationsMapper.class);

    ApplicationsResponse toApplicationsResponse(Applications applications);

    Applications toApplications(ApplicationsRequest applicationsRequest);

    SocioEconomic toSocioEconomic(SocioEconomicRequest request);

    Tutor toTutor(TutorRequest request);

    SchoolData toSchoolData(SchoolDataRequest request);


}