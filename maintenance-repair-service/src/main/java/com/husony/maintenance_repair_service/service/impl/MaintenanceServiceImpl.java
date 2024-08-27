package com.husony.maintenance_repair_service.service.impl;

import com.husony.maintenance_repair_service.pojo.Maintenance;
import com.husony.maintenance_repair_service.repository.MaintenanceRepository;
import com.husony.maintenance_repair_service.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {
    @Autowired
    private MaintenanceRepository maintenanceRepository;
    @Override
    public List<Maintenance> getMaintenance(Map<String, String> params) {
        return this.maintenanceRepository.getMaintenance(params);
    }

    @Override
    public void addOrUpdate(Maintenance l) {
        this.maintenanceRepository.addOrUpdate(l);
    }

    @Override
    public Maintenance getMaintenanceById(Long id) {
        return this.maintenanceRepository.getMaintenanceById(id);
    }

    @Override
    public void deleteMaintenance(Long id) {
        this.maintenanceRepository.deleteMaintenance(id);
    }
}
