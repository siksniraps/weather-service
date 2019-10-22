package lv.id.siksniraps.weatherservice.controller;

import lv.id.siksniraps.weatherservice.model.Weather;
import lv.id.siksniraps.weatherservice.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;
    private Environment environment;

    @Autowired
    public WeatherController(WeatherService weatherService, Environment environment) {
        this.weatherService = weatherService;
        this.environment = environment;
    }

    @GetMapping
    public ResponseEntity<Weather> getWeather(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        if (validateIp(ip)) {
            return ok(weatherService.fetchWeatherFromIp(ip));
        }
        return badRequest().build();
    }

    @GetMapping("/{ip}")
    public ResponseEntity<Weather> testGetWeather(@PathVariable("ip") String ip) {
        if (validateIp(ip)) {
            return ok(weatherService.fetchWeatherFromIp(ip));
        }
        return badRequest().build();
    }

    // check if ip is not local or loopback
    private boolean validateIp(String ip) {
        try {
            InetAddress inetAddr = InetAddress.getByName(ip);
            if (!environment.acceptsProfiles(Profiles.of("dev")) && (inetAddr.isAnyLocalAddress() || inetAddr.isLoopbackAddress())) {
                return false;
            }
        } catch (UnknownHostException e) {
            return false;
        }
        return true;
    }

}
