package lv.id.siksniraps.weatherservice.service;

import lv.id.siksniraps.weatherservice.component.GeoLocationComponent;
import lv.id.siksniraps.weatherservice.model.Location;
import org.springframework.stereotype.Service;

@Service
public class GeoLocationServiceImpl implements GeoLocationService {

    private final GeoLocationComponent geoLocationComponent;

    public GeoLocationServiceImpl(GeoLocationComponent geoLocationComponent) {
        this.geoLocationComponent = geoLocationComponent;
    }

    public Location fetchLocationFromIp(String ip) {
        return geoLocationComponent.fetchLocation(ip).getBody();
    }

}
