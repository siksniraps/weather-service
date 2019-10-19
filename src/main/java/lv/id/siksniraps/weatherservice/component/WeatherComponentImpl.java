package lv.id.siksniraps.weatherservice.component;

import lv.id.siksniraps.weatherservice.config.WeatherApiPropertyConfig;
import lv.id.siksniraps.weatherservice.model.Weather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherComponentImpl implements WeatherComponent {

    private Logger logger = LoggerFactory.getLogger(WeatherComponentImpl.class);

    private final RestTemplate restTemplate;
    private final WeatherApiPropertyConfig config;

    @Autowired
    public WeatherComponentImpl(RestTemplate restTemplate, WeatherApiPropertyConfig config) {
        this.restTemplate = restTemplate;
        this.config = config;
    }

    public ResponseEntity<Weather> fetchWeatherByCity(String city) {
        logger.debug("Fetching weather data for city=" + city);
        return restTemplate.getForEntity(
                config.getUrl() + "?units=metric&APPID={apiKey}&q={city}",
                Weather.class,
                config.getKey(), city
        );
    }


}
