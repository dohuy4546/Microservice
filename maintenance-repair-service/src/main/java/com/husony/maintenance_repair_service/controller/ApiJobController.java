package com.husony.maintenance_repair_service.controller;

import com.husony.maintenance_repair_service.dto.JobDTO;
import com.husony.maintenance_repair_service.pojo.Job;
import com.husony.maintenance_repair_service.pojo.Repair;
import com.husony.maintenance_repair_service.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/mainservice")
public class ApiJobController {
    @Autowired
    private JobService jobService;


    @GetMapping("/job")
    public ResponseEntity<List<Job>> getJob(@RequestParam Map<String, String> params) {
        return new ResponseEntity<List<Job>>(this.jobService.getJob(params), HttpStatus.OK);
    }

    @GetMapping("/job/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable("id") Long id) {
        Job d = this.jobService.getJobById(id);
        return new ResponseEntity<Job>(this.jobService.getJobById(id), HttpStatus.OK);
    }

    @PostMapping("/job/addOrUpdate")
    @ResponseStatus(HttpStatus.OK)
    public void addOrUpdate(@RequestBody JobDTO c){
        Job j = this.jobService.convertDtoToEntity(c);
        System.out.println(j);
        this.jobService.addOrUpdate(j);
    }

    @DeleteMapping("/job/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteJob(@PathVariable(value = "id") long id) {
        this.jobService.deleteJob(id);
    }
}
