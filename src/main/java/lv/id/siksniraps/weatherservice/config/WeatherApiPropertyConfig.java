package lv.id.siksniraps.weatherservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "weather-api")
public class WeatherApiPropertyConfig {
    private String url;
    private String key;
}
