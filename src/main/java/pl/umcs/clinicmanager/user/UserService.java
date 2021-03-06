package pl.umcs.clinicmanager.user;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.umcs.clinicmanager.user.domain.User;
import pl.umcs.clinicmanager.user.dto.UserDTO;
import pl.umcs.clinicmanager.user.dto.UserResponseDTO;
import pl.umcs.clinicmanager.user.mapper.UserMapper;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@org.springframework.stereotype.Service
public class UserService {


    private final UserRepository userRepository;
    private final UserMapper mapper;
    private PasswordEncoder passwordEncoder;


    public User addUser(UserDTO newUser) {

        final User mapped = mapper.DtoToEntity(newUser);
        if(Objects.isNull(mapped.getRole())) {
            mapped.setRole(Role.PATIENT);
        }
        mapped.setPassword(passwordEncoder.encode(mapped.getPassword()));
        return userRepository.save(mapped);

    }

    public UserResponseDTO getUser(Long userId) {
        final User foundUser = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);

        return mapper.entityToResponseDto(foundUser);

    }

    public UserDTO getUserByUsername(String username) {
        final User foundUser = userRepository.findByUsername(username).orElseThrow(NoSuchElementException::new);

        return mapper.entityToDto(foundUser);
    }

    public List<UserResponseDTO> getAllUsers() {

        final List<User> userList = userRepository.findAll();

        return mapper.entityListToResponseDtoList(userList);

    }

    @Transactional
    @Modifying
    public void deleteUser(Long userId) {
        final User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        userRepository.delete(user);
    }

    public void updateUser(Long id, UserDTO changedUser) {
        Optional<User> userOptional = userRepository.findById(id);
        userOptional.ifPresentOrElse(user -> {
            user.setUsername(changedUser.getUser_username());
            user.setPassword(changedUser.getUser_password());
            user.setUserDetails(mapper.DtoToEntity(changedUser.getUser_userDetails()));
            userRepository.save(userOptional.get());
        }, NoSuchElementException::new);

    }
}

