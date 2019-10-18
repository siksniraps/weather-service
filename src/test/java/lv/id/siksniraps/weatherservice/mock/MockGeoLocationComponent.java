package lv.id.siksniraps.weatherservice.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import lv.id.siksniraps.weatherservice.component.GeoLocationComponent;
import lv.id.siksniraps.weatherservice.model.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@Primary
@Component
@Profile("dev")
public class MockGeoLocationComponent implements GeoLocationComponent {

    private Logger logger = LoggerFactory.getLogger(MockGeoLocationComponent.class);

    @Value("${geoLocationJson.Riga}")
    private String pathName;

    @Override
    public ResponseEntity<Location> fetchLocation(String ip) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Location weather = mapper.readValue(new ClassPathResource(pathName).getFile(), Location.class);
            return ok(weather);
        } catch (IOException e) {
            logger.debug("Failed to read geo location data from " + pathName, e);
            return notFound().build();
        }
    }
}
