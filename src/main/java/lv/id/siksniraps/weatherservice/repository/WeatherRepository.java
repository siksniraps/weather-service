package lv.id.siksniraps.weatherservice.repository;

import lv.id.siksniraps.weatherservice.entity.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherEntity, Long> {
}