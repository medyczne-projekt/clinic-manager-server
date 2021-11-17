package pl.umcs.clinicmanager.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user_details")
public class UserDetails {
    @Id
    @SequenceGenerator(
            name = "user_details_sequence",
            sequenceName = "user_details_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_details_sequence"
    )
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String gender;
    private String email;
    private String country;
    private String city;
    private String street;
    private String postal_code;
    @Column(name = "date_of_birth", columnDefinition = "DATE")
    private ZonedDateTime dateOfBirth;
}
