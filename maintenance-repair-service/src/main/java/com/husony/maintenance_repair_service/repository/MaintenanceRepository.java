package com.husony.maintenance_repair_service.repository;

import com.husony.maintenance_repair_service.pojo.Maintenance;

import java.util.List;
import java.util.Map;

public interface MaintenanceRepository {
    List<Maintenance> getMaintenance(Map<String, String> params);
    void addOrUpdate(Maintenance l);
    Maintenance getMaintenanceById(Long id);
    void deleteMaintenance(Long id);
}
