package lv.id.siksniraps.weatherservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "geolocation-api")
public class GeoLocationApiPropertyConfig {
    private String url;
    private String key;
}
