package pl.umcs.clinicmanager.receipt.domain;

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
    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "patient_id",
            referencedColumnName = "id"
    )
    private User patient;
    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "doctor_id",
            referencedColumnName = "id"
    )
    private User doctor;
}
