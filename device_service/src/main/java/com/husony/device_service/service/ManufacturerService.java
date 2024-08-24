package com.husony.device_service.service;

import com.husony.device_service.pojo.Category;
import com.husony.device_service.pojo.Manufacturer;

import java.util.List;
import java.util.Map;

public interface ManufacturerService {
    List<Manufacturer> getManufacturer(Map<String, String> params);
    void addOrUpdate(Manufacturer m);
    Manufacturer getManufacturerById(long id);
    void deleteManufacturer(long id);
}
