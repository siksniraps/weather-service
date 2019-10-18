package lv.id.siksniraps.weatherservice;

import lv.id.siksniraps.weatherservice.component.WeatherComponent;
import lv.id.siksniraps.weatherservice.config.WeatherApiPropertyConfig;
import lv.id.siksniraps.weatherservice.model.Weather;
import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class ModelDeserializationTest {

    private MockRestServiceServer mockServer;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WeatherComponent weatherComponent;

    @Test
    void testWeatherDeserialization() {
        mockServer = MockRestServiceServer.createServer(restTemplate);

        String weatherJsonResponseRiga = "weather/weatherResponseRiga.json";

        try {
            mockServer.expect(MockRestRequestMatchers.anything())
                    .andRespond(MockRestResponseCreators.withSuccess(
                            Streams.asString((new ClassPathResource(weatherJsonResponseRiga)).getInputStream()),
                            MediaType.APPLICATION_JSON)
                    );
        } catch (IOException e) {
            e.printStackTrace();
        }

        ResponseEntity<Weather> weatherResponse = weatherComponent.fetchWeatherByCity("Riga");
        Weather weather = weatherResponse.getBody();

        // check deserialization of different types
        assertEquals(12.44, Objects.requireNonNull(weather).getTemperature());
        assertEquals(93, weather.getHumidity());
        assertEquals("clear sky", weather.getDescription());

    }

}

@Configuration
@Profile("test")
class WeatherPropertiesConfig {
    public WeatherApiPropertyConfig config () {
        WeatherApiPropertyConfig config = new WeatherApiPropertyConfig();
        config.setUrl("localhost");
        config.setKey("test");
        return config;
    }
}
