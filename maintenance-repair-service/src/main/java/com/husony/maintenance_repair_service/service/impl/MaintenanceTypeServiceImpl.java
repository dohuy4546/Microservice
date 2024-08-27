package com.husony.maintenance_repair_service.service.impl;

import com.husony.maintenance_repair_service.pojo.MaintenanceType;
import com.husony.maintenance_repair_service.repository.MaintenanceTypeRepository;
import com.husony.maintenance_repair_service.service.MaintenanceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MaintenanceTypeServiceImpl implements MaintenanceTypeService {
    @Autowired
    private MaintenanceTypeRepository maintenanceTypeRepository;

    @Override
    public List<MaintenanceType> getMaintenanceType(Map<String, String> params) {
        return this.maintenanceTypeRepository.getMaintenanceType(params);
    }

    @Override
    public void addOrUpdate(MaintenanceType l) {
        this.maintenanceTypeRepository.addOrUpdate(l);
    }

    @Override
    public MaintenanceType getMaintenanceTypeById(Long id) {
        return this.maintenanceTypeRepository.getMaintenanceTypeById(id);
    }

    @Override
    public void deleteMaintenanceType(Long id) {
        this.maintenanceTypeRepository.deleteMaintenanceType(id);
    }
}
