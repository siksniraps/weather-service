package lv.id.siksniraps.weatherservice.component;

import lv.id.siksniraps.weatherservice.config.GeoLocationApiPropertyConfig;
import lv.id.siksniraps.weatherservice.config.WeatherApiPropertyConfig;
import lv.id.siksniraps.weatherservice.model.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class GeoLocationComponentImpl implements GeoLocationComponent {

    private Logger logger = LoggerFactory.getLogger(WeatherComponentImpl.class);

    private final RestTemplate restTemplate;
    private final GeoLocationApiPropertyConfig config;

    @Autowired
    public GeoLocationComponentImpl(RestTemplate restTemplate, GeoLocationApiPropertyConfig config) {
        this.restTemplate = restTemplate;
        this.config = config;
    }

    public ResponseEntity<Location> fetchLocation(String ip) {
        logger.debug("Fetching location data for ip=" + ip);
        return restTemplate.getForEntity(config.getUrl() + "?apiKey={apiKey}&ip={ip}",
                Location.class, config.getKey(), ip);
    }

}
