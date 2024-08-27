package com.husony.maintenance_repair_service.controller;

import com.husony.maintenance_repair_service.pojo.Report;
import com.husony.maintenance_repair_service.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/mainservice")
public class ApiReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/report")
    public ResponseEntity<List<Report>> getReport(@RequestParam Map<String, String> params) {
        return new ResponseEntity<List<Report>>(this.reportService.getReport(params), HttpStatus.OK);
    }

    @GetMapping("/report/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable("id") Long id) {
        Report d = this.reportService.getReportById(id);
        return new ResponseEntity<Report>(this.reportService.getReportById(id), HttpStatus.OK);
    }

    @PostMapping("/report/addOrUpdate")
    @ResponseStatus(HttpStatus.OK)
    public void addOrUpdate(@RequestBody Report c){
        this.reportService.addOrUpdate(c);
    }

    @DeleteMapping("/report/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReport(@PathVariable(value = "id") long id) {
        this.reportService.deleteReport(id);
    }
}
