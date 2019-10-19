package lv.id.siksniraps.weatherservice.unitTests.component;

import lv.id.siksniraps.weatherservice.component.WeatherComponent;
import lv.id.siksniraps.weatherservice.model.Weather;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;

import static lv.id.siksniraps.weatherservice.util.Util.mockNextJsonResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.MockRestServiceServer.createServer;

@SpringBootTest
@ActiveProfiles("test")
class WeatherComponentTest {

    private RestTemplate restTemplate;
    private WeatherComponent weatherComponent;

    private MockRestServiceServer mockServer;

    @Value("${weather-json.riga}")
    private String weatherJsonResponseRiga;
    @Value("${weather-json.bad-response}")
    private String badResponse;

    @Autowired
    WeatherComponentTest(RestTemplate restTemplate, WeatherComponent weatherComponent) {
        this.restTemplate = restTemplate;
        this.weatherComponent = weatherComponent;
    }

    @PostConstruct
    void setUp() {
        mockServer = createServer(restTemplate);
    }

    @Test
    void testWeatherDeserialization() throws IOException {
        mockNextJsonResponse(mockServer, weatherJsonResponseRiga);

        ResponseEntity<Weather> weatherResponse = weatherComponent.fetchWeatherByCity("Riga");
        Weather weather = weatherResponse.getBody();

        Weather expected = new Weather()
                .setDescription("clear sky")
                .setHumidity(93d)
                .setPressure(1012d)
                .setTemperature(12.44)
                .setTemperatureMax(13d)
                .setTemperatureMin(11.67)
                .setWindSpeed(2.6)
                .setWindDirectionDegree(150d);

        assertEquals(expected, weather);
    }

    @Test
    void testIncorrectJsonResponse() throws IOException {
        mockNextJsonResponse(mockServer, badResponse);

        ResponseEntity<Weather> weatherResponse = weatherComponent.fetchWeatherByCity("Riga");
        //Location location = locationResponse.getBody();

    }

}
