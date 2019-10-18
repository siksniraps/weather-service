package lv.id.siksniraps.weatherservice.config;

import lv.id.siksniraps.weatherservice.component.GeoLocationComponent;
import lv.id.siksniraps.weatherservice.component.WeatherComponent;
import lv.id.siksniraps.weatherservice.mock.MockGeoLocationComponent;
import lv.id.siksniraps.weatherservice.mock.MockWeatherComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public WeatherComponent weatherComponent() {
        return new MockWeatherComponent();
    }

    @Bean
    public GeoLocationComponent geoLocationComponent() {
        return new MockGeoLocationComponent();
    }

}
