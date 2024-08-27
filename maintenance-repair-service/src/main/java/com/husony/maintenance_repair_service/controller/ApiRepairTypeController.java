package com.husony.maintenance_repair_service.controller;

import com.husony.maintenance_repair_service.pojo.RepairType;
import com.husony.maintenance_repair_service.service.RepairTypeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/mainservice")
public class ApiRepairTypeController {
    @Autowired
    private RepairTypeService repairTypeService;

    @GetMapping("/repairtype")
    public ResponseEntity<List<RepairType>> getRepairType(@RequestParam Map<String, String> params) {
        return new ResponseEntity<List<RepairType>>(this.repairTypeService.getRepairType(params), HttpStatus.OK);
    }

    @GetMapping("/repairtype/{id}")
    public ResponseEntity<RepairType> getRepairTypeById(@PathVariable("id") Long id) {
        RepairType d = this.repairTypeService.getRepairTypeById(id);
        return new ResponseEntity<RepairType>(this.repairTypeService.getRepairTypeById(id), HttpStatus.OK);
    }

    @PostMapping("/repairtype/addOrUpdate")
    @ResponseStatus(HttpStatus.OK)
    public void addOrUpdate(@RequestBody RepairType c){
        this.repairTypeService.addOrUpdate(c);
    }

    @DeleteMapping("/repairtype/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRepairType(@PathVariable(value = "id") long id) {
        this.repairTypeService.deleteRepairType(id);
    }
}
