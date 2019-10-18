package lv.id.siksniraps.weatherservice.component;

import lv.id.siksniraps.weatherservice.model.Location;
import org.springframework.http.ResponseEntity;

public interface GeoLocationComponent {
    ResponseEntity<Location> fetchLocation(String ip);
}
