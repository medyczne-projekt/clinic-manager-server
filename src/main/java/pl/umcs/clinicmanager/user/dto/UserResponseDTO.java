package pl.umcs.clinicmanager.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.umcs.clinicmanager.user.Role;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserResponseDTO {
    private String user_id;
    private String user_username;
    private Role user_role;
    private UserDetailsDTO user_userDetails;
}
