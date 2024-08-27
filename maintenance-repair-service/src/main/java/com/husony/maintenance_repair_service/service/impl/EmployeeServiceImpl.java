package com.husony.maintenance_repair_service.service.impl;

import com.husony.maintenance_repair_service.pojo.Employee;
import com.husony.maintenance_repair_service.repository.EmployeeRepository;
import com.husony.maintenance_repair_service.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public List<Employee> getEmployee(Map<String, String> params) {
        return this.employeeRepository.getEmployee(params);
    }

    @Override
    public void addOrUpdate(Employee l) {
        this.employeeRepository.addOrUpdate(l);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return this.employeeRepository.getEmployeeById(id);
    }

    @Override
    public void deleteEmployee(Long id) {
        this.employeeRepository.deleteEmployee(id);
    }
}
