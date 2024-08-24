package com.husony.device_service.repository;

import com.husony.device_service.pojo.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ManufacturerRepository {
    List<Manufacturer> getManufacturer(Map<String, String> params);
    void addOrUpdate(Manufacturer m);
    Manufacturer getManufacturerById(Long id);
    void deleteManufacturer(Long id);
}
