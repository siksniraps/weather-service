package lv.id.siksniraps.weatherservice.service;

import lv.id.siksniraps.weatherservice.model.Weather;

import javax.servlet.UnavailableException;

public interface WeatherService {
    Weather fetchWeatherFromIp(String ip) throws UnavailableException;
}
