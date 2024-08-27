package com.husony.maintenance_repair_service.service;

import com.husony.maintenance_repair_service.pojo.MaintenanceDevice;

import java.util.List;
import java.util.Map;

public interface MaintenanceDeviceService {
    List<MaintenanceDevice> getMaintenanceDevice(Map<String, String> params);
    void addOrUpdate(MaintenanceDevice l);
    MaintenanceDevice getMaintenanceDeviceById(Long id);
    void deleteMaintenanceDevice(Long id);
}
