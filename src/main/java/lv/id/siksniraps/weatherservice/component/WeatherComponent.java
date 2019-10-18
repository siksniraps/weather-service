package lv.id.siksniraps.weatherservice.component;

import lv.id.siksniraps.weatherservice.model.Weather;
import org.springframework.http.ResponseEntity;

public interface WeatherComponent {
    ResponseEntity<Weather> fetchWeatherByCity(String city);
}
