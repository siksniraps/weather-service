package lv.id.siksniraps.weatherservice.unitTests;

import lv.id.siksniraps.weatherservice.component.GeoLocationComponent;
import lv.id.siksniraps.weatherservice.component.WeatherComponent;
import lv.id.siksniraps.weatherservice.util.TestData;
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
        tripCircuitBreaker(() -> weatherComponent.fetchWeather(TestData.RIGA));
        mockNextJsonResponse(mockServer, weatherJsonResponseRiga);
        waitForCircuitBreakerToOpen();
        assertTrue(weatherComponent.fetchWeather(TestData.RIGA).isEmpty());
    }

    @Test
    void testGeolocationCircuitBreaker() throws IOException, InterruptedException {
        tripCircuitBreaker(() -> geoLocationComponent.fetchLocation(TestData.RIGA.getIp()));
        mockNextJsonResponse(mockServer, geolocationJsonResponseRiga);
        waitForCircuitBreakerToOpen();
        assertTrue(geoLocationComponent.fetchLocation(TestData.RIGA.getIp()).isEmpty());
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
