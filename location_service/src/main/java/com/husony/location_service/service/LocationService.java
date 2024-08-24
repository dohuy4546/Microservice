package com.husony.location_service.service;

import com.husony.location_service.pojo.Location;

import java.util.List;
import java.util.Map;

public interface LocationService {
    List<Location> getLocation(Map<String, String> params);
    Location getLocationById(long id);
    void addOrUpdate(Location l);
    void deleteLocation(long id);
}
