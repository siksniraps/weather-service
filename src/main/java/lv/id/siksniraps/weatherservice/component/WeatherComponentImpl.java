package lv.id.siksniraps.weatherservice.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import lv.id.siksniraps.weatherservice.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherComponentImpl implements WeatherComponent {

    private String url;
    private String apiKey;

    @Autowired
    public WeatherComponentImpl(
            @Value("${weatherApi.url}")String url,
            @Value("${weatherApi.apiKey}")String apiKey) {
        this.url = url;
        this.apiKey = apiKey;
    }


    public ResponseEntity<Weather> fetchWeatherByCity(String city) {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        return restTemplate.getForEntity(url + "?APPID={apiKey}&q={city}", Weather.class, apiKey, city);
    }


}
