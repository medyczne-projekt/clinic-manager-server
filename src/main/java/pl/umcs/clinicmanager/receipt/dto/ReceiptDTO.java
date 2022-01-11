package pl.umcs.clinicmanager.receipt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.umcs.clinicmanager.user.domain.User;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReceiptDTO {
    private String receiptDescription;
    private LocalDate receiptPrescriptionDate;
    private LocalDate receiptValidTo;
    private Long receiptPatientId;
    private Long receiptDoctorId;
}
