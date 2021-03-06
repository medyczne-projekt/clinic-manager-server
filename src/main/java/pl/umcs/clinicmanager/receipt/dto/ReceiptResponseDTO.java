package pl.umcs.clinicmanager.receipt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReceiptResponseDTO {
    private Long receiptId;
    private String receiptDescription;
    private String receiptCode;
    private LocalDate receiptPrescriptionDate;
    private LocalDate receiptValidTo;
    private Long receiptPatientId;
    private Long receiptDoctorId;
}
