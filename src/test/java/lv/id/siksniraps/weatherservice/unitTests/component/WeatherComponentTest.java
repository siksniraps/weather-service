package lv.id.siksniraps.weatherservice.unitTests.component;

import lv.id.siksniraps.weatherservice.component.WeatherComponent;
import lv.id.siksniraps.weatherservice.model.Weather;
import lv.id.siksniraps.weatherservice.util.TestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
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
        Weather weather = weatherComponent.fetchWeather(TestData.RIGA).orElseThrow();
        assertEquals(TestData.WEATHER_RIGA, weather);
    }

}
