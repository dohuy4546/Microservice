package com.husony.location_service.service.impl;

import com.husony.location_service.pojo.LocationHistory;
import com.husony.location_service.repository.LocationHistoryRepository;
import com.husony.location_service.service.LocationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LocationHistoryServiceImpl implements LocationHistoryService {
    @Autowired
    private LocationHistoryRepository locationHistoryRepository;
    @Override
    public List<LocationHistory> getLocationHistory(Map<String, String> params) {
        return this.locationHistoryRepository.getLocationHistory(params);
    }

    @Override
    public LocationHistory getLocationHistoryById(long id) {
        return this.locationHistoryRepository.getLocationHistoryById(id);
    }

    @Override
    public void addOrUpdate(LocationHistory l) {
        Map<String, String> params = new HashMap<>();
        params.put("deviceId", String.valueOf(l.getDevice()));
        params.put("active", String.valueOf(l.isActive()));
        List<LocationHistory> list = this.locationHistoryRepository.getLocationHistory(params);
        System.out.println(list);
        if (list != null && !list.isEmpty()){
            LocationHistory location = list.get(0);
            location.setActive(false);
            Date now = new Date();
            location.setEndDate(now);
            this.locationHistoryRepository.addOrUpdate(location);
        }
        this.locationHistoryRepository.addOrUpdate(l);
    }

    @Override
    public void deleteLocationHistory(long id) {
        this.locationHistoryRepository.deleteLocationHistory(id);
    }
}
