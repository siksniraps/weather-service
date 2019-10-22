package lv.id.siksniraps.weatherservice.service;

import lv.id.siksniraps.weatherservice.component.GeoLocationComponent;
import lv.id.siksniraps.weatherservice.exception.ExternalServiceUnavailableException;
import lv.id.siksniraps.weatherservice.model.Location;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static lv.id.siksniraps.weatherservice.config.CacheConfig.LOCATION_CACHE;

@Service
public class GeoLocationServiceImpl implements GeoLocationService {

    public static final String GEOLOCATION_SERVICE_UNAVAILABLE = "Geolocation service is temporarily unavailable";

    private final GeoLocationComponent geoLocationComponent;

    public GeoLocationServiceImpl(GeoLocationComponent geoLocationComponent) {
        this.geoLocationComponent = geoLocationComponent;
    }

    @Cacheable(value = LOCATION_CACHE, key = "#ip")
    @Override
    public Location fetchLocationFromIp(String ip) {
        return geoLocationComponent.fetchLocation(ip)
                .orElseThrow(() -> new ExternalServiceUnavailableException(GEOLOCATION_SERVICE_UNAVAILABLE));
    }

}