package pl.umcs.clinicmanager.medical_visit;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.umcs.clinicmanager.medical_visit.domain.MedicalVisit;

import java.util.List;

public interface MedicalVisitRepository extends JpaRepository<MedicalVisit, Long> {
    List<MedicalVisit> findAllByPatientId(Long id);
    List<MedicalVisit> findAllByDoctorId(Long id);
}
