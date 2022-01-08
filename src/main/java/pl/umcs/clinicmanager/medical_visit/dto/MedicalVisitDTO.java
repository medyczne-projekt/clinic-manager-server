package pl.umcs.clinicmanager.medical_visit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class MedicalVisitDTO {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String visitDescription;
    private Long idOfPatient;
    private Long idOfDoctor;
}
