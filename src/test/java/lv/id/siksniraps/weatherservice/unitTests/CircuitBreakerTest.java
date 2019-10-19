package lv.id.siksniraps.weatherservice.unitTests;

import lv.id.siksniraps.weatherservice.component.GeoLocationComponent;
import lv.id.siksniraps.weatherservice.component.WeatherComponent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

import java.io.IOException;

import static java.lang.Thread.sleep;
import static lv.id.siksniraps.weatherservice.util.Util.mockNextJsonResponse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.anything;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = AFTER_CLASS)
class CircuitBreakerTest {

    private MockRestServiceServer mockServer;

    private RestTemplate restTemplate;
    private WeatherComponent weatherComponent;
    private GeoLocationComponent geoLocationComponent;

    @Value("${weather-json.riga}")
    private String weatherJsonResponseRiga;
    @Value("${geolocation-json.riga}")
    private String geolocationJsonResponseRiga;

    @Autowired
    CircuitBreakerTest(RestTemplate restTemplate, WeatherComponent weatherComponent, GeoLocationComponent geoLocationComponent) {
        this.restTemplate = restTemplate;
        this.weatherComponent = weatherComponent;
        this.geoLocationComponent = geoLocationComponent;
    }

    @PostConstruct
    void setUp() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void testWeatherCircuitBreaker() throws IOException, InterruptedException {
        tripCircuitBreaker(() -> weatherComponent.fetchWeatherByCity("Riga"));
        mockNextJsonResponse(mockServer, weatherJsonResponseRiga);
        waitForCircuitBreakerToOpen();
        assertTrue(weatherComponent.fetchWeatherByCity("Riga").isEmpty());
    }

    @Test
    void testGeolocationCircuitBreaker() throws IOException, InterruptedException {
        tripCircuitBreaker(() -> geoLocationComponent.fetchLocation("127.0.0.1"));
        mockNextJsonResponse(mockServer, geolocationJsonResponseRiga);
        waitForCircuitBreakerToOpen();
        assertTrue(geoLocationComponent.fetchLocation("127.0.0.1").isEmpty());
    }

    private void tripCircuitBreaker(Runnable action) {
        for (int i = 0; i < 20; i++) {
            mockServer.expect(anything()).andRespond(withServerError());
            action.run();
            mockServer.reset();
        }
    }

    private void waitForCircuitBreakerToOpen() throws InterruptedException {
        sleep(1000);
    }
}