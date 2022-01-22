package project.automate_me.config;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import project.automate_me.model.CustomUserDetails;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    JwtTokenGenerator jwtTokenGenerator;
    SecurityProperties securityProperties;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtTokenGenerator jwtTokenGenerator, SecurityProperties securityProperties) {
        super(authenticationManager);
        this.jwtTokenGenerator = jwtTokenGenerator;
        this.securityProperties = securityProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String jwtToken = request.getHeader(securityProperties.getHeaderString());

        if (jwtToken != null && jwtToken.startsWith(securityProperties.getTokenPrefix())) {
            try {
                Authentication authentication = verifyJwtToken(jwtToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (SignatureVerificationException | TokenExpiredException ex) {
                log.error(ex.getMessage());
            }
        }

        chain.doFilter(request, response);

    }

    private Authentication verifyJwtToken(String jwtToken) {
        String userName = jwtTokenGenerator.getAccountNumberFromToken(jwtToken);

        CustomUserDetails customUserDetails = new CustomUserDetails(userName);
        return new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
    }
}
