package lv.id.siksniraps.weatherservice.util;

import lv.id.siksniraps.weatherservice.model.Location;
import lv.id.siksniraps.weatherservice.model.Weather;

public class TestData {

    public static Weather WEATHER_RIGA = new Weather()
            .setDescription("clear sky")
            .setHumidity(93d)
            .setPressure(1012d)
            .setTemperature(12.44)
            .setTemperatureMax(13d)
            .setTemperatureMin(11.67)
            .setWindSpeed(2.6)
            .setWindDirectionDegree(150d);

    public static Location RIGA = new Location()
            .setCity("Riga")
            .setCountry("Latvia")
            .setIp("92.240.64.0")
            .setLatitude("56.94860")
            .setLongitude("24.11820");
}
