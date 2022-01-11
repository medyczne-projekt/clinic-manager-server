package pl.umcs.clinicmanager.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.umcs.clinicmanager.security.JwtUtil;
import pl.umcs.clinicmanager.user.domain.User;
import pl.umcs.clinicmanager.user.dto.UserDTO;
import pl.umcs.clinicmanager.user.dto.UserResponseDTO;
import pl.umcs.clinicmanager.user.mapper.UserMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.FORBIDDEN;


@AllArgsConstructor

@RestController
@RequestMapping("/api")
public class UserController {
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final UserMapper mapper;

    @PostMapping("/user/add")
    ResponseEntity<User> addUser(@RequestBody UserDTO newUser) {
        final User addedUser = userService.addUser(newUser);
        return new ResponseEntity<>(addedUser, HttpStatus.CREATED);
    }

    @GetMapping("/user/{id}")
    ResponseEntity<UserResponseDTO> getUser(@PathVariable(name = "id") Long userId) {
        UserResponseDTO user = userService.getUser(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/users")
    ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
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

    @PostMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());

                String username = jwtUtil.decodeUsernameFromToken(refreshToken);
                UserDTO user = userService.getUserByUsername(username);
                String access_token = jwtUtil.createAccessTokenForDomainUser(mapper.DtoToEntity(user));

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refreshToken);
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                // response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
