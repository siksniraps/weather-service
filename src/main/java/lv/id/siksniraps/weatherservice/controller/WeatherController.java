package lv.id.siksniraps.weatherservice.controller;

import lv.id.siksniraps.weatherservice.model.Weather;
import lv.id.siksniraps.weatherservice.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/")
    public Weather getWeather(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        return weatherService.fetchWeatherFromIp(ip);
    }

}
