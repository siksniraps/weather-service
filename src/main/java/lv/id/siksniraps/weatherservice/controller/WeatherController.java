package lv.id.siksniraps.weatherservice.controller;

import lv.id.siksniraps.weatherservice.model.Weather;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("weather")
public class WeatherController {

    @GetMapping("/")
    public Weather getWeather() {
        throw new NotImplementedException();
    }

}
