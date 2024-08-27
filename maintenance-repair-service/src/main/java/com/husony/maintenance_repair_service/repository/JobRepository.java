package com.husony.maintenance_repair_service.repository;

import com.husony.maintenance_repair_service.pojo.Job;

import java.util.List;
import java.util.Map;

public interface JobRepository {
    List<Job> getJob(Map<String, String> params);
    void addOrUpdate(Job l);
    Job getJobById(Long id);
    void deleteJob(Long id);
}
