package lv.id.siksniraps.weatherservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
    private Double temperature;
    private Double temperatureMin;
    private Double temperatureMax;
    private String description;
    private Integer pressure;
    private Integer humidity;

    private Double windSpeed;
    private Integer windDirectionDegree;

    @JsonProperty("weather")
    private void unpackWeather(List<Map<String, Object>> weather) {
        this.description = (String) weather.stream()
                .findFirst()
                .map(w -> w.get("description"))
                .orElse("");
    }

    @JsonProperty("main")
    private void unpackMain(Map<String, Object> main) {
        this.temperature = (Double) main.get("temp");
        this.pressure = (Integer) main.get("pressure");
        this.humidity = (Integer) main.get("humidity");
        this.temperatureMin = (Double) main.get("temp_min");
        this.temperatureMax = (Double) main.get("temp_max");
    }

    @JsonProperty("wind")
    private void unpackWind(Map<String, Object> wind) {
        this.windSpeed = (Double) wind.get("speed");
        this.windDirectionDegree = (Integer) wind.get("deg");
    }


}
