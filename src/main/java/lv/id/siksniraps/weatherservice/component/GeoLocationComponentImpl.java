package lv.id.siksniraps.weatherservice.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import lv.id.siksniraps.weatherservice.model.Location;
import lv.id.siksniraps.weatherservice.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GeoLocationComponentImpl implements GeoLocationComponent {
    private String url;
    private String apiKey;

    @Autowired
    public GeoLocationComponentImpl(
            @Value("${geoLocationApi.url}")String url,
            @Value("${geoLocationApi.apiKey}")String apiKey) {
        this.url = url;
        this.apiKey = apiKey;
    }


    public ResponseEntity<Location> fetchLocation(String ip) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(url + "?apiKey={apiKey}&ip={ip}", Location.class, apiKey, ip);
    }

}
