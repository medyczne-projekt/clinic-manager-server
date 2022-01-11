package pl.umcs.clinicmanager.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import pl.umcs.clinicmanager.config.JwtProperties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

// TODO: this class should be better
@Component
public class JwtUtil {

    private JwtProperties jwtProperties;

    public JwtUtil(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public String createAccessTokenForPrincipal(User user) {
        Algorithm algorithm = Algorithm.HMAC256(jwtProperties.getSecret().getBytes());
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtProperties.getAccessTokenTimeout().toMillis()))
                .withIssuer(jwtProperties.getIssuer())
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
    }

    public String createAccessTokenForDomainUser(pl.umcs.clinicmanager.user.domain.User user) {
        Algorithm algorithm = Algorithm.HMAC256(jwtProperties.getSecret().getBytes());
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtProperties.getAccessTokenTimeout().toMillis()))
                .withIssuer(jwtProperties.getIssuer())
                .withClaim("roles", List.of(user.getRole().toString()))
                .sign(algorithm);
    }

    public String createRefreshTokenForPrincipal(User user) {
        Algorithm algorithm = Algorithm.HMAC256(jwtProperties.getSecret().getBytes());
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtProperties.getRefreshTokenTimeout().toMillis()))
                .withIssuer(jwtProperties.getIssuer())
                .sign(algorithm);
    }

    public String decodeUsernameFromToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(jwtProperties.getSecret().getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }

    public UsernamePasswordAuthenticationToken usernamePasswordAuthenticationTokenFromJWT(String jwtToken) {
        Algorithm algorithm = Algorithm.HMAC256(jwtProperties.getSecret().getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(jwtToken);
        String username = decodedJWT.getSubject();
        String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        stream(roles).forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role));
        });
        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }
}
