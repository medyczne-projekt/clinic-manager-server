package pl.umcs.clinicmanager.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Component
@ConfigurationProperties("jwt")
public class JwtProperties {

    private String secret;
    private String issuer;
    @DurationUnit(ChronoUnit.SECONDS)
    private Duration accessTokenTimeout;
    @DurationUnit(ChronoUnit.SECONDS)
    private Duration refreshTokenTimeout;

    public String getSecret() {
        return secret;
    }

    public String getIssuer() {
        return issuer;
    }


    public Duration getAccessTokenTimeout() {
        return accessTokenTimeout;
    }

    public Duration getRefreshTokenTimeout() {
        return refreshTokenTimeout;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public void setAccessTokenTimeout(Duration accessTokenTimeout) {
        this.accessTokenTimeout = accessTokenTimeout;
    }

    public void setRefreshTokenTimeout(Duration refreshTokenTimeout) {
        this.refreshTokenTimeout = refreshTokenTimeout;
    }
}
