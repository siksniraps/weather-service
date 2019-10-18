package lv.id.siksniraps.weatherservice.service;

import lv.id.siksniraps.weatherservice.component.WeatherComponent;
import lv.id.siksniraps.weatherservice.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final GeoLocationService geoLocationService;
    private final WeatherComponent weatherComponent;


    @Autowired
    public WeatherServiceImpl(GeoLocationService geoLocationService, WeatherComponent weatherComponent) {
        this.geoLocationService = geoLocationService;
        this.weatherComponent = weatherComponent;
    }

    public Weather fetchWeatherFromIp(String ip) {
        String city = "Riga";

        ResponseEntity<Weather> weatherResponse =  weatherComponent.fetchWeatherByCity(city);
        return weatherResponse.getBody();
    }


}
