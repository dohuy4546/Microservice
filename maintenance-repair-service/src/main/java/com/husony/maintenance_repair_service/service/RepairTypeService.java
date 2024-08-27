package com.husony.maintenance_repair_service.service;

import com.husony.maintenance_repair_service.pojo.RepairType;

import java.util.List;
import java.util.Map;

public interface RepairTypeService {
    List<RepairType> getRepairType(Map<String, String> params);
    void addOrUpdate(RepairType l);
    RepairType getRepairTypeById(Long id);
    void deleteRepairType(Long id);
}
