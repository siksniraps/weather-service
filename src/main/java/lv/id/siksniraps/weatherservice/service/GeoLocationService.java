package lv.id.siksniraps.weatherservice.service;

import lv.id.siksniraps.weatherservice.model.Location;

import javax.servlet.UnavailableException;

public interface GeoLocationService {
    Location fetchLocationFromIp(String ip) throws UnavailableException;
}
