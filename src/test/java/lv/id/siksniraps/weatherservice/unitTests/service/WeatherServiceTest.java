package lv.id.siksniraps.weatherservice.unitTests.service;

import lv.id.siksniraps.weatherservice.exception.ExternalServiceUnavailableException;
import lv.id.siksniraps.weatherservice.model.Weather;
import lv.id.siksniraps.weatherservice.service.WeatherService;
import lv.id.siksniraps.weatherservice.util.TestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class WeatherServiceTest {

    private final WeatherService weatherService;

    @Autowired
    WeatherServiceTest(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Test
    void testFetchLocation() {
        Weather weather = weatherService.fetchWeatherFromIp(TestData.RIGA.getIp());
        assertEquals(TestData.WEATHER_RIGA, weather);
    }

    @Test
    void testFailedRetrievingLocation() {
        assertThrows(
                ExternalServiceUnavailableException.class,
                () -> weatherService.fetchWeatherFromIp("not an ip")
        );
    }

}
