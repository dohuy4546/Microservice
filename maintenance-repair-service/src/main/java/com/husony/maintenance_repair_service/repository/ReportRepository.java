package com.husony.maintenance_repair_service.repository;

import com.husony.maintenance_repair_service.pojo.Report;

import java.util.List;
import java.util.Map;

public interface ReportRepository {
    List<Report> getReport(Map<String, String> params);
    void addOrUpdate(Report l);
    Report getReportById(Long id);
    void deleteReport(Long id);
}
