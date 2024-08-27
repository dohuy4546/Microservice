package com.husony.maintenance_repair_service.controller;

import com.husony.maintenance_repair_service.pojo.Employee;
import com.husony.maintenance_repair_service.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/mainservice")
public class ApiEmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> getEmployee(@RequestParam Map<String, String> params) {
        return new ResponseEntity<List<Employee>>(this.employeeService.getEmployee(params), HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
        Employee d = this.employeeService.getEmployeeById(id);
        return new ResponseEntity<Employee>(this.employeeService.getEmployeeById(id), HttpStatus.OK);
    }

    @PostMapping("/employee/addOrUpdate")
    @ResponseStatus(HttpStatus.OK)
    public void addOrUpdate(@RequestBody Employee c){
        this.employeeService.addOrUpdate(c);
    }

    @DeleteMapping("/employee/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable(value = "id") long id) {
        this.employeeService.deleteEmployee(id);
    }
}
