package lv.id.siksniraps.weatherservice.config;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@EnableCaching
@Configuration
public class CacheConfig {

    public final static String LOCATION_CACHE = "location";
    public final static String WEATHER_CACHE = "weather";

    @Bean
    public Cache locationCache() {
        return new ConcurrentMapCache(
                LOCATION_CACHE,
                CacheBuilder.newBuilder()
                        .maximumSize(1000) // currently ok, but might need to change
                        .build().asMap(),
                false
        );
    }

    @Bean
    public Cache weatherCache() {
        return new ConcurrentMapCache(
                WEATHER_CACHE,
                CacheBuilder.newBuilder()
                        .expireAfterWrite(60, TimeUnit.SECONDS)
                        .build().asMap(),
                false
        );
    }
}
