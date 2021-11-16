package pl.umcs.clinicmanager.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.umcs.clinicmanager.user.domain.User;
import pl.umcs.clinicmanager.user.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "user_firstName", target = "firstName")
    @Mapping(source = "user_lastName", target = "lastName")
    @Mapping(source = "user_email", target = "email")
    User DtoToEntity(UserDTO user);


    @Mapping(source = "firstName", target = "user_firstName")
    @Mapping(source = "lastName", target = "user_lastName")
    @Mapping(source = "email", target = "user_email")
    UserDTO entityToDto(User user);

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
