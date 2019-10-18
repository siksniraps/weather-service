package lv.id.siksniraps.weatherservice.mock;

import lv.id.siksniraps.weatherservice.component.GeoLocationComponent;
import lv.id.siksniraps.weatherservice.model.Location;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.http.ResponseEntity;

public class MockGeoLocationComponent implements GeoLocationComponent {
    @Override
    public ResponseEntity<Location> fetchLocation(String ip) {
        throw new NotImplementedException();
    }
}
