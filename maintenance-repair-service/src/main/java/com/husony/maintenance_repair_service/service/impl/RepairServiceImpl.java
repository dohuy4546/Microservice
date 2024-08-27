package com.husony.maintenance_repair_service.service.impl;

import com.husony.maintenance_repair_service.dto.RepairDTO;
import com.husony.maintenance_repair_service.pojo.Repair;
import com.husony.maintenance_repair_service.pojo.RepairType;
import com.husony.maintenance_repair_service.pojo.Report;
import com.husony.maintenance_repair_service.repository.RepairRepository;
import com.husony.maintenance_repair_service.service.RepairService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RepairServiceImpl implements RepairService {
    @Autowired
    private RepairRepository repairRepository;

    @Override
    public List<Repair> getRepair(Map<String, String> params) {
        return this.repairRepository.getRepair(params);
    }

    @Override
    public void addOrUpdate(Repair l) {
        Date date = new Date();
        l.setCreatedDate(date);
        this.repairRepository.addOrUpdate(l);
    }

    @Override
    public Repair getRepairById(Long id) {
        return this.repairRepository.getRepairById(id);
    }

    @Override
    public void deleteRepair(Long id) {
        this.repairRepository.deleteRepair(id);
    }

    @Override
    public Repair convertDtoToEntity(RepairDTO l) {
        Repair r = new Repair();
        BeanUtils.copyProperties(l, r);
        RepairType repairType = new RepairType();
        repairType.setId(l.getRepairTypeId());
        r.setRepairTypeId(repairType);
        Report report = new Report();
        report.setId(l.getReportId());
        r.setReportId(report);
        return r;
    }
}
