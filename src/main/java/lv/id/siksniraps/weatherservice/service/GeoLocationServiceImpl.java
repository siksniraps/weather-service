package lv.id.siksniraps.weatherservice.service;

import lv.id.siksniraps.weatherservice.component.GeoLocationComponent;
import lv.id.siksniraps.weatherservice.exception.ExternalServiceUnavailableException;
import lv.id.siksniraps.weatherservice.model.Location;
import org.springframework.stereotype.Service;

import javax.servlet.UnavailableException;

@Service
public class GeoLocationServiceImpl implements GeoLocationService {

    private final GeoLocationComponent geoLocationComponent;

    public GeoLocationServiceImpl(GeoLocationComponent geoLocationComponent) {
        this.geoLocationComponent = geoLocationComponent;
    }

    @Override
    public Location fetchLocationFromIp(String ip) throws UnavailableException {
        return geoLocationComponent.fetchLocation(ip)
                .orElseThrow(() -> new ExternalServiceUnavailableException("Geolocation service is temporary unavailable"));
    }

}
