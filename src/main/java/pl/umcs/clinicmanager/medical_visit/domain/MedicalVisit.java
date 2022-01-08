package pl.umcs.clinicmanager.medical_visit.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.umcs.clinicmanager.user.domain.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "medical_visits")
public class MedicalVisit {

    @Id
    @SequenceGenerator(
            name = "medical_visit_sequence",
            sequenceName = "medical_visits_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "medical_visit_sequence"
    )
    private Long id;
    @Column(name = "start_date_of_visit")
    private LocalDateTime startDateOfVisit;
    @Column(name = "end_date_of_visit")
    private LocalDateTime endDateOfVisit;
    @Column(name = "description")
    private String description;


    @JoinColumn(name = "patient_id", insertable = false, updatable = false)
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    private User patient;
    @Column(name = "patient_id")
    private Long patientId;

    @JoinColumn(name = "doctor_id", insertable = false, updatable = false)
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    private User doctor;
    @Column(name = "doctor_id")
    private Long doctorId;
}
