package com.husony.maintenance_repair_service.service;

import com.husony.maintenance_repair_service.pojo.MaintenanceType;

import java.util.List;
import java.util.Map;

public interface MaintenanceTypeService {
    List<MaintenanceType> getMaintenanceType(Map<String, String> params);
    void addOrUpdate(MaintenanceType l);
    MaintenanceType getMaintenanceTypeById(Long id);
    void deleteMaintenanceType(Long id);
}
