package pl.umcs.clinicmanager.receipt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.umcs.clinicmanager.user.domain.User;

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

    @Column(name = "valid_to")
    private LocalDate validTo;

    @JoinColumn(name = "patient_id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JsonIgnore
    private User patient;
    @Column(name = "patient_id")
    private Long patientId;

    @JoinColumn(name = "doctor_id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JsonIgnore
    private User doctor;
    @Column(name = "doctor_id")
    private Long doctorId;
}
