package lv.id.siksniraps.weatherservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
    private String ip;
    private String city;
    @JsonProperty("country_name")
    private String country;
    private String latitude;
    private String longitude;
}
