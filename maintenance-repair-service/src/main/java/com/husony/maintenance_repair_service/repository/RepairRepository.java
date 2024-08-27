package com.husony.maintenance_repair_service.repository;

import com.husony.maintenance_repair_service.pojo.Repair;

import java.util.List;
import java.util.Map;

public interface RepairRepository {
    List<Repair> getRepair(Map<String, String> params);
    void addOrUpdate(Repair l);
    Repair getRepairById(Long id);
    void deleteRepair(Long id);
}
