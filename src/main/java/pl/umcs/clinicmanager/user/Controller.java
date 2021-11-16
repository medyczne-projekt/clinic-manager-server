package pl.umcs.clinicmanager.user;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.umcs.clinicmanager.user.domain.User;
import pl.umcs.clinicmanager.user.dto.UserDTO;

import java.util.List;


@AllArgsConstructor

@RestController
@RequestMapping("/api")
public class Controller {
    private final Service userService;

    @PostMapping("/user/add")
    ResponseEntity<User> addUser(@RequestBody UserDTO newUser) {
        final User addedUser = userService.addUser(newUser);
        return new ResponseEntity<>(addedUser, HttpStatus.CREATED);
    }

    @GetMapping("/user/{id}")
    ResponseEntity<UserDTO> getUser(@PathVariable(name = "id") Long userId) {
        UserDTO user = userService.getUser(userId);
        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }

    @GetMapping("/users")
    ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.FOUND);
    }

    @DeleteMapping("/user/delete/{userId}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<UserDTO>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/user/update/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody UserDTO modifiedUser) {
        userService.updateUser(id,modifiedUser);
    }
}
