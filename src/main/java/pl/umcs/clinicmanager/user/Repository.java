package pl.umcs.clinicmanager.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.umcs.clinicmanager.user.domain.User;

import java.util.List;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<User, Long> {



}
