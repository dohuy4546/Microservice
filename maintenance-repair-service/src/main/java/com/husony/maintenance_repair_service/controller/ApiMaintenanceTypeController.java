package com.husony.maintenance_repair_service.controller;

import com.husony.maintenance_repair_service.pojo.MaintenanceType;
import com.husony.maintenance_repair_service.service.MaintenanceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/mainservice")
public class ApiMaintenanceTypeController {
    @Autowired
    private MaintenanceTypeService maintenanceTypeService;

    @GetMapping("/maintenancetype")
    public ResponseEntity<List<MaintenanceType>> getMaintenanceType(@RequestParam Map<String, String> params) {
        return new ResponseEntity<List<MaintenanceType>>(this.maintenanceTypeService.getMaintenanceType(params), HttpStatus.OK);
    }

    @GetMapping("/maintenancetype/{id}")
    public ResponseEntity<MaintenanceType> getMaintenanceTypeById(@PathVariable("id") Long id) {
        MaintenanceType d = this.maintenanceTypeService.getMaintenanceTypeById(id);
        return new ResponseEntity<MaintenanceType>(this.maintenanceTypeService.getMaintenanceTypeById(id), HttpStatus.OK);
    }

    @PostMapping("/maintenancetype/addOrUpdate")
    @ResponseStatus(HttpStatus.OK)
    public void addOrUpdate(@RequestBody MaintenanceType c){
        this.maintenanceTypeService.addOrUpdate(c);
    }

    @DeleteMapping("/maintenancetype/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMaintenanceType(@PathVariable(value = "id") long id) {
        this.maintenanceTypeService.deleteMaintenanceType(id);
    }
}
