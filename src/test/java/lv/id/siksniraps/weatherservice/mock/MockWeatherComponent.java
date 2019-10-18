package lv.id.siksniraps.weatherservice.mock;

import lv.id.siksniraps.weatherservice.component.WeatherComponent;
import lv.id.siksniraps.weatherservice.model.Weather;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.http.ResponseEntity;

public class MockWeatherComponent implements WeatherComponent {
    @Override
    public ResponseEntity<Weather> fetchWeatherByCity(String city) {
        throw new NotImplementedException();

    }
}
