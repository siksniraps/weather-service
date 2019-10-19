package lv.id.siksniraps.weatherservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
    private String description;

    private Double temperature;
    private Double temperatureMin;
    private Double temperatureMax;

    private Double pressure;
    private Double humidity;

    private Double windSpeed;
    private Double windDirectionDegree;

    @JsonProperty("weather")
    private void unpackWeather(List<Map<String, Object>> weather) {
        this.description = (String) weather.stream()
                .findFirst()
                .map(w -> w.get("description"))
                .orElse("");
    }

    @JsonProperty("main")
    private void unpackMain(Map<String, Object> main) {
        this.temperature = Double.parseDouble(String.valueOf(main.get("temp")));
        this.pressure = Double.parseDouble(String.valueOf(main.get("pressure")));
        this.humidity = Double.parseDouble(String.valueOf(main.get("humidity")));
        this.temperatureMin = Double.parseDouble(String.valueOf(main.get("temp_min")));
        this.temperatureMax = Double.parseDouble(String.valueOf(main.get("temp_max")));
    }

    @JsonProperty("wind")
    private void unpackWind(Map<String, Object> wind) {
        this.windSpeed = (Double) wind.get("speed");
        this.windDirectionDegree = Double.parseDouble(String.valueOf(wind.get("deg")));
    }

}
