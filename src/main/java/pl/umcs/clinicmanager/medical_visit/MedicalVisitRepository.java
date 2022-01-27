package pl.umcs.clinicmanager.medical_visit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.umcs.clinicmanager.medical_visit.domain.MedicalVisit;

import java.time.LocalDateTime;
import java.util.List;

public interface MedicalVisitRepository extends JpaRepository<MedicalVisit, Long> {
    List<MedicalVisit> findAllByPatientId(Long id);
    List<MedicalVisit> findAllByDoctorId(Long id);

    @Query("select visit from MedicalVisit visit where ?1 <= visit.endDateOfVisit and ?2 >= visit.startDateOfVisit")
    List<MedicalVisit> getByTimeRange(LocalDateTime startDateOfVisit, LocalDateTime endDateOfVisit);
}
