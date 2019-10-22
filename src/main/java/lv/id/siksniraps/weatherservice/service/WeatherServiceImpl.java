package lv.id.siksniraps.weatherservice.service;

import lv.id.siksniraps.weatherservice.component.WeatherComponent;
import lv.id.siksniraps.weatherservice.entity.WeatherEntity;
import lv.id.siksniraps.weatherservice.exception.ExternalServiceUnavailableException;
import lv.id.siksniraps.weatherservice.model.Location;
import lv.id.siksniraps.weatherservice.model.Weather;
import lv.id.siksniraps.weatherservice.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static lv.id.siksniraps.weatherservice.config.CacheConfig.WEATHER_CACHE;

@Service
public class WeatherServiceImpl implements WeatherService {

    public static final String WEATHER_SERVICE_UNAVAILABLE = "Weather service is temporarily unavailable";

    private final GeoLocationService geoLocationService;
    private final WeatherComponent weatherComponent;
    private final WeatherRepository weatherRepository;

    @Autowired
    public WeatherServiceImpl(GeoLocationService geoLocationService, WeatherComponent weatherComponent, WeatherRepository weatherRepository) {
        this.geoLocationService = geoLocationService;
        this.weatherComponent = weatherComponent;
        this.weatherRepository = weatherRepository;
    }

    @Override
    public Weather fetchWeatherFromIp(String ip) {
        Location location = geoLocationService.fetchLocationFromIp(ip);
        Weather weather = fetchWeatherByCity(location);
        saveWeatherForLocation(weather, location);
        return weather;
    }

    @Cacheable(value = WEATHER_CACHE, key = "#location.city + ',' + #location.country")
    public Weather fetchWeatherByCity(Location location) {
        Optional<Weather> weatherResponse = weatherComponent.fetchWeather(location);
        return weatherResponse.orElseThrow(() -> new ExternalServiceUnavailableException(WEATHER_SERVICE_UNAVAILABLE));
    }

    private void saveWeatherForLocation(Weather weather, Location location) {
        WeatherEntity weatherEntity = new WeatherEntity()
                .setIp(location.getIp())
                .setCity(location.getCity())
                .setCountry(location.getCountry())
                .setLatitude(location.getLatitude())
                .setLongitude(location.getLongitude())
                .setDescription(weather.getDescription())
                .setTemperature(weather.getTemperature())
                .setTemperatureMin(weather.getTemperatureMin())
                .setTemperatureMax(weather.getTemperatureMax())
                .setHumidity(weather.getHumidity())
                .setPressure(weather.getPressure())
                .setWindSpeed(weather.getWindSpeed())
                .setWindDirectionDegree(weather.getWindDirectionDegree());
        weatherRepository.save(weatherEntity);
    }

}
