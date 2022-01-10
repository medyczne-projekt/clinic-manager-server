package pl.umcs.clinicmanager.medical_visit.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.umcs.clinicmanager.medical_visit.domain.MedicalVisit;
import pl.umcs.clinicmanager.medical_visit.dto.MedicalVisitDTO;
import pl.umcs.clinicmanager.medical_visit.dto.MedicalVisitResponseDTO;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicalVisitMapper {


    @Mapping(source = "startDate", target = "startDateOfVisit")
    @Mapping(source = "endDate", target = "endDateOfVisit")
    @Mapping(source = "visitDescription", target = "description")
    @Mapping(source = "idOfPatient", target = "patientId")
    @Mapping(source = "idOfDoctor", target = "doctorId")
    MedicalVisit dtoToEntity(MedicalVisitDTO medicalVisitDTO);


    @Mapping(source = "startDateOfVisit", target = "startDate")
    @Mapping(source = "endDateOfVisit", target = "endDate")
    @Mapping(source = "description", target = "visitDescription")
    @Mapping(source = "patientId", target = "idOfPatient")
    @Mapping(source = "doctorId", target = "idOfDoctor")
    MedicalVisitDTO entityToDto(MedicalVisit medicalVisit);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "startDateOfVisit", target = "startDate")
    @Mapping(source = "endDateOfVisit", target = "endDate")
    @Mapping(source = "description", target = "visitDescription")
    @Mapping(source = "patientId", target = "idOfPatient")
    @Mapping(source = "doctorId", target = "idOfDoctor")
    MedicalVisitResponseDTO entityToResponseDto(MedicalVisit medicalVisit);

    default List<MedicalVisitDTO> entityListToDtoList(List<MedicalVisit> visitList) {
        if (visitList == null)
            return null;
        List<MedicalVisitDTO> mappedList = new ArrayList<>(visitList.size());
        for (MedicalVisit visit : visitList)
            mappedList.add(entityToDto(visit));
        return mappedList;
    }

    default List<MedicalVisitResponseDTO> entityListToResponseDtoList(List<MedicalVisit> visitList) {
        if (visitList == null)
            return null;
        List<MedicalVisitResponseDTO> mappedList = new ArrayList<>(visitList.size());
        for (MedicalVisit visit : visitList)
            mappedList.add(entityToResponseDto(visit));
        return mappedList;
    }

}
