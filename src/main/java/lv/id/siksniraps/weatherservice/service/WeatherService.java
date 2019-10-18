package lv.id.siksniraps.weatherservice.service;

import lv.id.siksniraps.weatherservice.model.Weather;

public interface WeatherService {
    Weather fetchWeatherFromIp(String ip);
}
