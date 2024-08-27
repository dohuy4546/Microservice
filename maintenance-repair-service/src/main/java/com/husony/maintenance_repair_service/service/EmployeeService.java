package com.husony.maintenance_repair_service.service;

import com.husony.maintenance_repair_service.pojo.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    List<Employee> getEmployee(Map<String, String> params);
    void addOrUpdate(Employee l);
    Employee getEmployeeById(Long id);
    void deleteEmployee(Long id);
}
