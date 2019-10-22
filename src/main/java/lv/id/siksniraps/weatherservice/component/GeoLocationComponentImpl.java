package lv.id.siksniraps.weatherservice.component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import lv.id.siksniraps.weatherservice.config.GeoLocationApiPropertyConfig;
import lv.id.siksniraps.weatherservice.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;

@Slf4j
@Component
public class GeoLocationComponentImpl implements GeoLocationComponent {

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
        log.debug("Fetching location data for ip=" + ip);
        return ofNullable(restTemplate.getForEntity(config.getUrl() + "?apiKey={apiKey}&ip={ip}",
                Location.class, config.getKey(), ip).getBody());
    }

    @SuppressWarnings("unused")
    public Optional<Location> fallbackFetchLocation(String ip) {
        log.warn("External geolocation service unavailable");
        return empty();
    }

}
