package com.husony.maintenance_repair_service.repository;


import com.husony.maintenance_repair_service.pojo.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeRepository {
    List<Employee> getEmployee(Map<String, String> params);
    void addOrUpdate(Employee l);
    Employee getEmployeeById(Long id);
    void deleteEmployee(Long id);
}
