package lv.id.siksniraps.weatherservice.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "weather", schema = "weather_service")
@Data
@Accessors(chain = true)
public class WeatherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "weather_generator")
    @SequenceGenerator(name = "weather_generator", sequenceName = "weather_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "ip")
    private String ip;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "description")
    private String description;

    @Column(name = "temperature")
    private Double temperature;

    @Column(name = "temperature_min")
    private Double temperatureMin;

    @Column(name = "temperature_max")
    private Double temperatureMax;

    @Column(name = "pressure")
    private Double pressure;

    @Column(name = "humidity")
    private Double humidity;

    @Column(name = "wind_speed")
    private Double windSpeed;

    @Column(name = "wind_direction_degree")
    private Double windDirectionDegree;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
