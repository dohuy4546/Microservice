package com.husony.maintenance_repair_service.service.impl;

import com.husony.maintenance_repair_service.pojo.MaintenanceDevice;
import com.husony.maintenance_repair_service.repository.MaintenanceDeviceRepository;
import com.husony.maintenance_repair_service.repository.MaintenanceRepository;
import com.husony.maintenance_repair_service.service.MaintenanceDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MaintenanceDeviceServiceImpl implements MaintenanceDeviceService {
    @Autowired
    private MaintenanceDeviceRepository maintenanceDeviceRepository;

    @Override
    public List<MaintenanceDevice> getMaintenanceDevice(Map<String, String> params) {
        return this.maintenanceDeviceRepository.getMaintenanceDevice(params);
    }

    @Override
    public void addOrUpdate(MaintenanceDevice l) {
        this.maintenanceDeviceRepository.addOrUpdate(l);
    }

    @Override
    public MaintenanceDevice getMaintenanceDeviceById(Long id) {
        return this.maintenanceDeviceRepository.getMaintenanceDeviceById(id);
    }

    @Override
    public void deleteMaintenanceDevice(Long id) {
        this.maintenanceDeviceRepository.deleteMaintenanceDevice(id);
    }
}
