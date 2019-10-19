package lv.id.siksniraps.weatherservice.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import lv.id.siksniraps.weatherservice.component.WeatherComponent;
import lv.id.siksniraps.weatherservice.model.Weather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@Primary
@Component
@Profile("dev")
public class MockWeatherComponent implements WeatherComponent {

    private Logger logger = LoggerFactory.getLogger(MockWeatherComponent.class);

    @Value("${weather-json.riga}")
    private String pathName;

    @Override
    public ResponseEntity<Weather> fetchWeatherByCity(String city) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Weather weather = mapper.readValue(new ClassPathResource(pathName).getFile(), Weather.class);
            return ok(weather);
        } catch (IOException e) {
            logger.debug("Failed to read weather data from " + pathName, e);
            return notFound().build();
        }
    }
}
