package project.automate_me.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import project.automate_me.model.CustomUserDetails;

import java.sql.Date;

@Component
@Getter
public class JwtTokenGenerator {
    SecurityProperties securityProperties;

    public JwtTokenGenerator(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    public String generateTokenWithPrefix(CustomUserDetails customUserDetails) {
        return securityProperties.getTokenPrefix() + generateToken(customUserDetails);
    }

    public String generateToken(CustomUserDetails customUserDetails) {
        return JWT.create()
                .withSubject(customUserDetails.getUsername())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + securityProperties.getTokenExpirationTime()))
                .sign(Algorithm.HMAC512(securityProperties.getSecret().getBytes()));
    }

    public String getAccountNumberFromToken(String token){
        return JWT.require(Algorithm.HMAC512(securityProperties.getSecret().getBytes()))
                .build()
                .verify(token.replace(securityProperties.getTokenPrefix(),""))
                .getSubject();
    }
}