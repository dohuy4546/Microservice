package com.husony.maintenance_repair_service.service.impl;

import com.husony.maintenance_repair_service.pojo.RepairType;
import com.husony.maintenance_repair_service.repository.RepairTypeRepository;
import com.husony.maintenance_repair_service.service.RepairTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RepairTypeServiceImpl implements RepairTypeService {
    @Autowired
    private RepairTypeRepository repairTypeRepository;

    @Override
    public List<RepairType> getRepairType(Map<String, String> params) {
        return this.repairTypeRepository.getRepairType(params);
    }

    @Override
    public void addOrUpdate(RepairType l) {
        this.repairTypeRepository.addOrUpdate(l);
    }

    @Override
    public RepairType getRepairTypeById(Long id) {
        return this.repairTypeRepository.getRepairTypeById(id);
    }

    @Override
    public void deleteRepairType(Long id) {
        this.repairTypeRepository.deleteRepairType(id);
    }
}
