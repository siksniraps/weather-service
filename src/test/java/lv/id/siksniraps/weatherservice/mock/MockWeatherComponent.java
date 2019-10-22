package lv.id.siksniraps.weatherservice.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import lv.id.siksniraps.weatherservice.component.WeatherComponent;
import lv.id.siksniraps.weatherservice.model.Location;
import lv.id.siksniraps.weatherservice.model.Weather;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

@Primary
@Component
@Profile("dev")
public class MockWeatherComponent implements WeatherComponent {

    @Override
    public Optional<Weather> fetchWeather(Location location) {
        String pathName = "jsons/weather/" + location.getCity() + ".json";
        ObjectMapper mapper = new ObjectMapper();
        try {
            Weather weather = mapper.readValue(new ClassPathResource(pathName).getFile(), Weather.class);
            return of(weather);
        } catch (IOException e) {
            return empty();
        }
    }
}
