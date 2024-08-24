package com.husony.location_service.repository;

import com.husony.location_service.pojo.Location;
import com.husony.location_service.pojo.LocationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface LocationHistoryRepository  {
    List<LocationHistory> getLocationHistory(Map<String, String> params);
    void addOrUpdate(LocationHistory l);
    LocationHistory getLocationHistoryById(Long id);
    void deleteLocationHistory(Long id);
}
