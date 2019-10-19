package lv.id.siksniraps.weatherservice.unitTests.component;

import lv.id.siksniraps.weatherservice.component.GeoLocationComponent;
import lv.id.siksniraps.weatherservice.model.Location;
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

@SpringBootTest
@ActiveProfiles("test")
class GeoLocationComponentTest {

    private RestTemplate restTemplate;
    private GeoLocationComponent geoLocationComponent;

    private MockRestServiceServer mockServer;

    @Value("${geolocation-json.riga}")
    private String geoLocationJsonResponseRiga;
    @Value("${geolocation-json.bad-response}")
    private String badResponse;

    @Autowired
    GeoLocationComponentTest(RestTemplate restTemplate, GeoLocationComponent geoLocationComponent) {
        this.restTemplate = restTemplate;
        this.geoLocationComponent = geoLocationComponent;
    }

    @PostConstruct
    void setUp() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void testGeoLocationDeserialization() throws IOException {
        mockNextJsonResponse(mockServer, geoLocationJsonResponseRiga);

        ResponseEntity<Location> locationResponse = geoLocationComponent.fetchLocation("127.0.0.1");
        Location location = locationResponse.getBody();

        Location expected = new Location()
                .setCity("Riga")
                .setCountry("Latvia")
                .setIp("92.240.64.0")
                .setLatitude("56.94860")
                .setLongitude("24.11820");

        assertEquals(expected, location);
    }

}
