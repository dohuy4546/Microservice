package com.husony.location_service.service.impl;

import com.husony.location_service.pojo.Location;
import com.husony.location_service.repository.LocationRepository;
import com.husony.location_service.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<Location> getLocation(Map<String, String> params) {
        return this.locationRepository.getLocation(params);
    }

    @Override
    public Location getLocationById(long id) {
        return this.locationRepository.getLocationById(id);
    }

    @Override
    public void addOrUpdate(Location l) {
        this.locationRepository.addOrUpdate(l);
    }

    @Override
    public void deleteLocation(long id) {
        this.locationRepository.deleteLocation(id);
    }
}
