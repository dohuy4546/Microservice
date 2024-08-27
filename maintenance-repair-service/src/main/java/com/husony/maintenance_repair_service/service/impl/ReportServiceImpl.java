package com.husony.maintenance_repair_service.service.impl;

import com.husony.maintenance_repair_service.messaging.DeviceMessage;
import com.husony.maintenance_repair_service.messaging.DeviceMessageProducer;
import com.husony.maintenance_repair_service.pojo.Report;
import com.husony.maintenance_repair_service.repository.ReportRepository;
import com.husony.maintenance_repair_service.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private DeviceMessageProducer deviceMessageProducer;

    @Override
    public List<Report> getReport(Map<String, String> params) {
        return this.reportRepository.getReport(params);
    }

    @Override
    public void addOrUpdate(Report l) {
        Date date = new Date();
        l.setCreatedDate(date);
        DeviceMessage deviceMessage = new DeviceMessage();
        deviceMessage.setId(l.getDeviceId());
        if (l.getStatus() == null){
            l.setStatus("PENDING");
        }
        if(l.getId() == null && l.getStatus() == "PENDING" ){
            deviceMessage.setStatus("PENDING");
            deviceMessageProducer.sendMessage(deviceMessage);
        }else if (l.getId() != null && l.getStatus() == "PROCESSING"){
            deviceMessage.setStatus("REPAIR");
            deviceMessageProducer.sendMessage(deviceMessage);
        }
        else if (l.getId() != null && l.getStatus() == "DONE") {
            deviceMessage.setStatus("ACTIVE");
            deviceMessageProducer.sendMessage(deviceMessage);
        }
        this.reportRepository.addOrUpdate(l);
    }

    @Override
    public Report getReportById(Long id) {
        return this.reportRepository.getReportById(id);
    }

    @Override
    public void deleteReport(Long id) {
        this.reportRepository.deleteReport(id);
    }
}
