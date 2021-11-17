package pl.umcs.clinicmanager.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDetailsDTO {
    private String user_firstName;
    private String user_lastName;
    private String user_gender;
    private String user_email;
    private String user_country;
    private String user_city;
    private String user_street;
    private String user_postal_code;
    private ZonedDateTime user_dateOfBirth;
}
