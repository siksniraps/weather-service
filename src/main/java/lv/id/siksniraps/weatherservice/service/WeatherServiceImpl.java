package lv.id.siksniraps.weatherservice.service;

import lv.id.siksniraps.weatherservice.component.WeatherComponent;
import lv.id.siksniraps.weatherservice.exception.ExternalServiceUnavailableException;
import lv.id.siksniraps.weatherservice.model.Location;
import lv.id.siksniraps.weatherservice.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static lv.id.siksniraps.weatherservice.config.CacheConfig.WEATHER_CACHE;

@Service
public class WeatherServiceImpl implements WeatherService {

    public static final String WEATHER_SERVICE_UNAVAILABLE = "Weather service is temporary unavailable";

    private final GeoLocationService geoLocationService;
    private final WeatherComponent weatherComponent;

    @Autowired
    public WeatherServiceImpl(GeoLocationService geoLocationService, WeatherComponent weatherComponent) {
        this.geoLocationService = geoLocationService;
        this.weatherComponent = weatherComponent;
    }

    @Override
    public Weather fetchWeatherFromIp(String ip) {
        Location location = geoLocationService.fetchLocationFromIp(ip);
        return fetchWeatherByCity(location.getCity());
    }

    @Cacheable(value = WEATHER_CACHE, key = "#city")
    public Weather fetchWeatherByCity(String city) {
        Optional<Weather> weatherResponse = weatherComponent.fetchWeatherByCity(city);
        return weatherResponse.orElseThrow(() -> new ExternalServiceUnavailableException(WEATHER_SERVICE_UNAVAILABLE));
    }
}
