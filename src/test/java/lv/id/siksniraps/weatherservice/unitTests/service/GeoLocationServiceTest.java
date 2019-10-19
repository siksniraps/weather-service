package lv.id.siksniraps.weatherservice.unitTests.service;

import lv.id.siksniraps.weatherservice.exception.ExternalServiceUnavailableException;
import lv.id.siksniraps.weatherservice.model.Location;
import lv.id.siksniraps.weatherservice.service.GeoLocationService;
import lv.id.siksniraps.weatherservice.util.TestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class GeoLocationServiceTest {

    private final GeoLocationService geoLocationService;

    @Autowired
    GeoLocationServiceTest(GeoLocationService geoLocationService) {
        this.geoLocationService = geoLocationService;
    }

    @Test
    void testFetchLocation() {
        Location location = geoLocationService.fetchLocationFromIp(TestData.RIGA.getIp());
        assertEquals(TestData.RIGA, location);
    }

    @Test
    void testFailedRetrievingLocation() {
        assertThrows(
                ExternalServiceUnavailableException.class,
                () -> geoLocationService.fetchLocationFromIp("not an ip")
        );
    }

}
