package pl.umcs.clinicmanager.receipt.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "receipts")
public class Receipt {
    @Id
    @SequenceGenerator(
            name = "receipt_sequence",
            sequenceName = "receipts_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "receipt_sequence"
    )
    private Long id;
    private String description;
    @Column(name = "prescription_date")
    private LocalDate prescriptionDate;
    @Column(name = "patient_id")
    private Long patientID;
    @Column(name = "doctor_id")
    private Long doctorID;
}
