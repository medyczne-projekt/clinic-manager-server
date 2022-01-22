package pl.umcs.clinicmanager.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.umcs.clinicmanager.user.UserRepository;
import pl.umcs.clinicmanager.user.domain.User;

import java.util.Collection;
import java.util.List;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    log.error("User {} not found in the database", username);
                    throw new UsernameNotFoundException(String.format("User %s not found in the database", username));
                });
        Collection<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole().name()));
        org.springframework.security.core.userdetails.User springSecurityUser = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
        CustomPrincipal customPrincipal = new CustomPrincipal(
                springSecurityUser.getUsername(),
                springSecurityUser.getPassword(),
                springSecurityUser.isEnabled(),
                springSecurityUser.isAccountNonExpired(),
                springSecurityUser.isCredentialsNonExpired(),
                springSecurityUser.isAccountNonLocked(),
                springSecurityUser.getAuthorities()
        );
        customPrincipal.setUserId(user.getId());
        return customPrincipal;
    }
}
