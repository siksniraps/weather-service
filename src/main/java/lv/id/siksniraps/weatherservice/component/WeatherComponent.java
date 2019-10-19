package lv.id.siksniraps.weatherservice.component;

import lv.id.siksniraps.weatherservice.model.Location;
import lv.id.siksniraps.weatherservice.model.Weather;

import java.util.Optional;

public interface WeatherComponent {
    Optional<Weather> fetchWeather(Location location);
}
