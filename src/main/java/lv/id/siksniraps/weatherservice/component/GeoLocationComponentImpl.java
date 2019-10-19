package lv.id.siksniraps.weatherservice.component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lv.id.siksniraps.weatherservice.config.GeoLocationApiPropertyConfig;
import lv.id.siksniraps.weatherservice.model.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;

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

    @HystrixCommand(fallbackMethod = "fallbackFetchLocation")
    @Override
    public Optional<Location> fetchLocation(String ip) {
        logger.debug("Fetching location data for ip=" + ip);
        return ofNullable(restTemplate.getForEntity(config.getUrl() + "?apiKey={apiKey}&ip={ip}",
                Location.class, config.getKey(), ip).getBody());
    }

    @SuppressWarnings("unused")
    public Optional<Location> fallbackFetchLocation (String ip) {
        logger.warn("External geolocation service unavailable");
        return empty();
    }

}
