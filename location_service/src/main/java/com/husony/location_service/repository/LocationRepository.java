package com.husony.location_service.repository;

import com.husony.location_service.pojo.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface LocationRepository {
    List<Location> getLocation(Map<String, String> params);
    void addOrUpdate(Location l);
    Location getLocationById(Long id);
    void deleteLocation(Long id);
}
