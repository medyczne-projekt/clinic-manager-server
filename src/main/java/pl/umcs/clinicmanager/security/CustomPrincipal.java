package pl.umcs.clinicmanager.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomPrincipal extends User {
    public CustomPrincipal(String username, String password, boolean enabled, boolean accountNonExpired,
                       boolean credentialsNonExpired, boolean accountNonLocked,
                       Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
