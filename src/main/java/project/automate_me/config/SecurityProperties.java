package project.automate_me.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "security")
@PropertySource("classpath:security.properties")
@Getter
@Setter
public class SecurityProperties {
    private Long tokenExpirationTime;
    private String secret;
    private String tokenPrefix;
    private String headerString;

    public void setTokenExpirationTime(String tokenExpirationTime) {
        this.tokenExpirationTime = Long.parseLong(tokenExpirationTime);
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix + " ";
    }
}
