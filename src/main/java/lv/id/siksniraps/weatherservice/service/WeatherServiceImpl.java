package lv.id.siksniraps.weatherservice.service;

public class WeatherServiceImpl implements WeatherService {

    final GeoLocationService geoLocationService;

    public WeatherServiceImpl(GeoLocationService geoLocationService) {
        this.geoLocationService = geoLocationService;
    }
}
