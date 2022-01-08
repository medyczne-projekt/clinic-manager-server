package pl.umcs.clinicmanager.medical_visit;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import pl.umcs.clinicmanager.medical_visit.domain.MedicalVisit;
import pl.umcs.clinicmanager.medical_visit.dto.MedicalVisitDTO;
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
    public MedicalVisit addMedicalVisit(MedicalVisitDTO newMedicalVisit) {
        final MedicalVisit mapped = mapper.dtoToEntity(newMedicalVisit);
        return medicalVisitRepository.save(mapped);
    }

    public List<MedicalVisitDTO> getAllVisitsByPatientId(Long patientId) {
        final List<MedicalVisit> visits = medicalVisitRepository.findAllByPatientId(patientId);
        if (visits.isEmpty()) throw new NoSuchElementException();
        return mapper.entityListToDtoList(visits);

    }

    public List<MedicalVisitDTO> getAllVisitsByDoctorId(Long doctorId) {
        final List<MedicalVisit> visits = medicalVisitRepository.findAllByDoctorId(doctorId);
        if (visits.isEmpty()) throw new NoSuchElementException();
        return mapper.entityListToDtoList(visits);
    }

    public MedicalVisitDTO getVisitById(Long visitId) {
        final MedicalVisit found = medicalVisitRepository.findById(visitId).orElseThrow(NoSuchElementException::new);
        return mapper.entityToDto(found);

    }

    @Transactional
    @Modifying
    public void deleteVisit(Long id) {
        final MedicalVisit visit = medicalVisitRepository.findById(id).orElseThrow(NoSuchElementException::new);
        medicalVisitRepository.delete(visit);
    }
}
