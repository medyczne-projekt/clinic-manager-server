package pl.umcs.clinicmanager.receipt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.umcs.clinicmanager.receipt.domain.Receipt;

import java.util.List;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

    public List<Receipt> findAllByPatientId(Long id);

    public List<Receipt> findAllByDoctorId(Long id);
}
