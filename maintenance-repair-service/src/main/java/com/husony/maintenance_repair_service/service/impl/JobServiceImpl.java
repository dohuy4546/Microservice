package com.husony.maintenance_repair_service.service.impl;

import com.husony.maintenance_repair_service.dto.JobDTO;
import com.husony.maintenance_repair_service.messaging.DeviceMessage;
import com.husony.maintenance_repair_service.messaging.DeviceMessageProducer;
import com.husony.maintenance_repair_service.pojo.*;
import com.husony.maintenance_repair_service.repository.JobRepository;
import com.husony.maintenance_repair_service.service.JobService;
import com.husony.maintenance_repair_service.service.RepairService;
import com.husony.maintenance_repair_service.service.ReportService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ReportService reportService;

    @Autowired
    private DeviceMessageProducer deviceMessageProducer;

    @Override
    public List<Job> getJob(Map<String, String> params) {
        return this.jobRepository.getJob(params);
    }

    @Override
    public void addOrUpdate(Job l) {
        LocalDateTime date = LocalDateTime.now();
        l.setUpdatedDate(date);
        if (l.getId() != null
                && l.getRepairId() != null) {
            Job job = this.jobRepository.getJobById(l.getId());
            Report report = this.reportService
                    .getReportById(job.getRepairId().getReportId().getId());
            if (l.getStatus() == "DONE") {
                report.setStatus("DONE");
            } else if (l.getStatus() == "PROCESSING") {
                report.setStatus("PROCESSING");
            }
            this.reportService.addOrUpdate(report);
        } else if (l.getId() != null
                && l.getMaintenanceId() != null) {
            Job job = this.jobRepository.getJobById(l.getId());
            Set<MaintenanceDevice> maintenanceDevice =
                    job.getMaintenanceId().getMaintenanceDeviceId();
            for (MaintenanceDevice device : maintenanceDevice) {
                String status = "PENDING";
                if (l.getStatus() == "DONE") {
                    status = "ACTIVE";
                } else if (l.getStatus() == "PROCESSING") {
                    status = "MAINTENANCE";
                }
                DeviceMessage message = new DeviceMessage(device.getDeviceId(), status);
                this.deviceMessageProducer.sendMessage(message);
            }
        }

        this.jobRepository.addOrUpdate(l);
    }

    @Override
    public Job getJobById(Long id) {
        return this.jobRepository.getJobById(id);
    }

    @Override
    public void deleteJob(Long id) {
        this.jobRepository.deleteJob(id);
    }

    @Override
    public Job convertDtoToEntity(JobDTO l) {
        Job j = new Job();
        BeanUtils.copyProperties(l, j);
        Employee e = new Employee();
        e.setId(l.getEmployeeId());
        j.setEmployeeId(e);
        if (l.getMaintenanceId() != null && l.getRepairId() == null) {
            Maintenance m = new Maintenance();
            m.setId(l.getMaintenanceId());
            j.setMaintenanceId(m);
            j.setRepairId(null);
        } else if (l.getRepairId() != null && l.getMaintenanceId() == null) {
            Repair r = new Repair();
            r.setId(l.getRepairId());
            j.setRepairId(r);
            j.setMaintenanceId(null);
        }
        return j;
    }
}
