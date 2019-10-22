package lv.id.siksniraps.weatherservice.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import lv.id.siksniraps.weatherservice.component.GeoLocationComponent;
import lv.id.siksniraps.weatherservice.model.Location;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

@Slf4j
@Primary
@Component
@Profile("dev")
public class MockGeoLocationComponent implements GeoLocationComponent {

    @Override
    public Optional<Location> fetchLocation(String ip) {
        String pathName = "jsons/geoLocation/" + ip + ".json";
        ObjectMapper mapper = new ObjectMapper();
        try {
            Location weather = mapper.readValue(new ClassPathResource(pathName).getFile(), Location.class);
            return of(weather);
        } catch (IOException e) {
            log.debug("Failed to read geo location data from " + pathName, e);
            return empty();
        }
    }
}
