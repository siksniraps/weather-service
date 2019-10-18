package lv.id.siksniraps.weatherservice.service;

import lv.id.siksniraps.weatherservice.model.Location;

public interface GeoLocationService {
    Location fetchLocationFromIp(String ip);
}
