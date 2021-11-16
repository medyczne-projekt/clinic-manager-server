package pl.umcs.clinicmanager.user.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private String user_firstName;
    private String user_lastName;
    private String user_email;
}
