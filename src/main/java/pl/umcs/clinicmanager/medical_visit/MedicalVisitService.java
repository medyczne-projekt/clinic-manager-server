package pl.umcs.clinicmanager.medical_visit;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import pl.umcs.clinicmanager.medical_visit.domain.MedicalVisit;
import pl.umcs.clinicmanager.medical_visit.dto.MedicalVisitDTO;
import pl.umcs.clinicmanager.medical_visit.dto.MedicalVisitResponseDTO;
import pl.umcs.clinicmanager.medical_visit.exception.InvalidVisitException;
import pl.umcs.clinicmanager.medical_visit.mapper.MedicalVisitMapper;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@AllArgsConstructor
public class MedicalVisitService {

    private final MedicalVisitMapper mapper;
    private final MedicalVisitRepository medicalVisitRepository;

    @Transactional
    public MedicalVisit addMedicalVisit(MedicalVisitDTO newMedicalVisit) throws InvalidVisitException {
        final MedicalVisit mapped = mapper.dtoToEntity(newMedicalVisit);

        final List<MedicalVisit> foundedVisitsByTimeRange = medicalVisitRepository.getByTimeRange(mapped.getStartDateOfVisit(), mapped.getEndDateOfVisit());

        if(mapped.getStartDateOfVisit().isAfter(mapped.getEndDateOfVisit())) {
            throw new InvalidVisitException("the start date of the visit cannot be after the end date");
        }

        if (mapped.getEndDateOfVisit().isBefore(mapped.getStartDateOfVisit())) {
           throw new  InvalidVisitException("the end date of the visit cannot be before the start date");
        }

        if (!foundedVisitsByTimeRange.isEmpty()) {
            throw new InvalidVisitException("this visit overlaps with existing");
        }

        System.out.println("FOUNDED VISITS:");
        System.out.println(foundedVisitsByTimeRange);
        return medicalVisitRepository.save(mapped);
    }

    public List<MedicalVisitResponseDTO> getAllVisitsByPatientId(Long patientId) {
        final List<MedicalVisit> visits = medicalVisitRepository.findAllByPatientId(patientId);
        if (visits.isEmpty()) throw new NoSuchElementException();
        return mapper.entityListToResponseDtoList(visits);

    }

    public List<MedicalVisitResponseDTO> getAllVisitsByDoctorId(Long doctorId) {
        final List<MedicalVisit> visits = medicalVisitRepository.findAllByDoctorId(doctorId);
        if (visits.isEmpty()) throw new NoSuchElementException();
        return mapper.entityListToResponseDtoList(visits);
    }

    public MedicalVisitResponseDTO getVisitById(Long visitId) {
        final MedicalVisit found = medicalVisitRepository.findById(visitId).orElseThrow(NoSuchElementException::new);
        return mapper.entityToResponseDto(found);

    }

    @Transactional
    @Modifying
    public void deleteVisit(Long id) {
        final MedicalVisit visit = medicalVisitRepository.findById(id).orElseThrow(NoSuchElementException::new);
        medicalVisitRepository.delete(visit);
    }


}
