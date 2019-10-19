package lv.id.siksniraps.weatherservice.component;

import lv.id.siksniraps.weatherservice.model.Location;

import java.util.Optional;

public interface GeoLocationComponent {
    Optional<Location> fetchLocation(String ip);
}
