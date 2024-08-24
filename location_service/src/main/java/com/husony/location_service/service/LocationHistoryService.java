package com.husony.location_service.service;

import com.husony.location_service.pojo.Location;
import com.husony.location_service.pojo.LocationHistory;

import java.util.List;
import java.util.Map;

public interface LocationHistoryService {
    List<LocationHistory> getLocationHistory(Map<String, String> params);
    LocationHistory getLocationHistoryById(long id);
    void addOrUpdate(LocationHistory l);
    void deleteLocationHistory(long id);
}
