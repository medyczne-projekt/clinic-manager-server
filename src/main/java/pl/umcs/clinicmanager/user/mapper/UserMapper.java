package pl.umcs.clinicmanager.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.umcs.clinicmanager.user.domain.User;
import pl.umcs.clinicmanager.user.domain.UserDetails;
import pl.umcs.clinicmanager.user.dto.UserDTO;
import pl.umcs.clinicmanager.user.dto.UserDetailsDTO;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "user_username", target = "username")
    @Mapping(source = "user_password", target = "password")
    @Mapping(source = "user_userDetails", target = "userDetails")
    User DtoToEntity(UserDTO user);

    @Mapping(source = "username", target = "user_username")
    @Mapping(source = "password", target = "user_password")
    @Mapping(source = "userDetails", target = "user_userDetails")
    UserDTO entityToDto(User user);

    @Mapping(source = "user_firstName", target = "firstName")
    @Mapping(source = "user_lastName", target = "lastName")
    @Mapping(source = "user_gender", target = "gender")
    @Mapping(source = "user_email", target = "email")
    @Mapping(source = "user_country", target = "country")
    @Mapping(source = "user_city", target = "city")
    @Mapping(source = "user_street", target = "street")
    @Mapping(source = "user_postal_code", target = "postal_code")
    @Mapping(source = "user_dateOfBirth", target = "dateOfBirth")
    UserDetails DtoToEntity(UserDetailsDTO userDetails);

    @Mapping(source = "firstName", target = "user_firstName")
    @Mapping(source = "lastName", target = "user_lastName")
    @Mapping(source = "gender", target = "user_gender")
    @Mapping(source = "email", target = "user_email")
    @Mapping(source = "country", target = "user_country")
    @Mapping(source = "city", target = "user_city")
    @Mapping(source = "street", target = "user_street")
    @Mapping(source = "postal_code", target = "user_postal_code")
    @Mapping(source = "dateOfBirth", target = "user_dateOfBirth")
    UserDetailsDTO entityToDto(UserDetails userDetails);

    default List<UserDTO> entityListToDtoList(List<User> userList) {
        if (userList == null) {
            return null;
        }
        List<UserDTO> mappedList = new ArrayList<>(userList.size());
        for (User user : userList) {
            mappedList.add(entityToDto(user));
        }

        return mappedList;
    }

}
