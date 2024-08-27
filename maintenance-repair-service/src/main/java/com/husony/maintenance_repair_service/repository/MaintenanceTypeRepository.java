package com.husony.maintenance_repair_service.repository;

import com.husony.maintenance_repair_service.pojo.MaintenanceType;

import java.util.List;
import java.util.Map;

public interface MaintenanceTypeRepository {
    List<MaintenanceType> getMaintenanceType(Map<String, String> params);
    void addOrUpdate(MaintenanceType l);
    MaintenanceType getMaintenanceTypeById(Long id);
    void deleteMaintenanceType(Long id);
}
