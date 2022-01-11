package pl.umcs.clinicmanager.user;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.umcs.clinicmanager.user.domain.User;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
