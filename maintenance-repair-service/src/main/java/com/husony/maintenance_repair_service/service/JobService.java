package com.husony.maintenance_repair_service.service;


import com.husony.maintenance_repair_service.dto.JobDTO;
import com.husony.maintenance_repair_service.pojo.Job;

import java.util.List;
import java.util.Map;

public interface JobService {
    List<Job> getJob(Map<String, String> params);
    void addOrUpdate(Job l);
    Job getJobById(Long id);
    void deleteJob(Long id);
    Job convertDtoToEntity(JobDTO l);
}
