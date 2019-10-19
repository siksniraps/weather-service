package lv.id.siksniraps.weatherservice.component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lv.id.siksniraps.weatherservice.config.WeatherApiPropertyConfig;
import lv.id.siksniraps.weatherservice.model.Location;
import lv.id.siksniraps.weatherservice.model.Weather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;

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

    @HystrixCommand(fallbackMethod = "fallbackFetchWeather")
    @Override
    public Optional<Weather> fetchWeather(Location location) {
        logger.debug("Fetching weather data for lat=" + location.getLatitude() + ", lon=" + location.getLongitude());
        ResponseEntity<Weather> response = restTemplate.getForEntity(
                config.getUrl() + "?units=metric&APPID={apiKey}&lat={lat}&lon={lon}",
                Weather.class,
                config.getKey(), location.getLatitude(), location.getLongitude()
        );
        return ofNullable(response.getBody());
    }

    // Don't like this. Easy to break when refactoring.
    @SuppressWarnings("unused")
    public Optional<Weather> fallbackFetchWeather(Location location) {
        logger.warn("External weather service unavailable");
        return empty();
    }

}
