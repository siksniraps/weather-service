package lv.id.siksniraps.weatherservice.unitTests.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lv.id.siksniraps.weatherservice.util.TestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

import static lv.id.siksniraps.weatherservice.service.GeoLocationServiceImpl.GEOLOCATION_SERVICE_UNAVAILABLE;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.anything;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class WeatherControllerTest {
    private MockMvc mockMvc;
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @Autowired
    WeatherControllerTest(MockMvc mockMvc, RestTemplate restTemplate) {
        this.mockMvc = mockMvc;
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    void setUp() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void testGetWeather() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(get("/weather").with(remoteAddr(TestData.RIGA.getIp())))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(TestData.WEATHER_RIGA)));
    }

    @Test
    void testGetWeatherBadIp() throws Exception {
        mockServer.expect(anything()).andRespond(withServerError());
        mockMvc.perform(get("/weather").with(remoteAddr("not an ip")))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetWeatherUnavailable() throws Exception {
        mockServer.expect(anything()).andRespond(withServerError());
        mockMvc.perform(get("/weather"))
                .andExpect(status().isServiceUnavailable())
                .andExpect(content().string(GEOLOCATION_SERVICE_UNAVAILABLE));
    }

    private static RequestPostProcessor remoteAddr(String ip) {
        return request -> {
            request.setRemoteAddr(ip);
            return request;
        };
    }
}
