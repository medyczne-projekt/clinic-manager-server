package pl.umcs.clinicmanager.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.umcs.clinicmanager.user.domain.UserDetails;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO {
    private String user_username;
    private String user_password;
    private UserDetailsDTO user_userDetails;
}
