package com.husony.maintenance_repair_service.controller;


import com.husony.maintenance_repair_service.dto.RepairDTO;
import com.husony.maintenance_repair_service.pojo.Repair;
import com.husony.maintenance_repair_service.pojo.RepairType;
import com.husony.maintenance_repair_service.service.RepairService;
import com.husony.maintenance_repair_service.service.RepairService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/mainservice")
public class ApiRepairController {
    @Autowired
    private RepairService repairService;

    @GetMapping("/repair")
    public ResponseEntity<List<Repair>> getRepair(@RequestParam Map<String, String> params) {
        return new ResponseEntity<List<Repair>>(this.repairService.getRepair(params), HttpStatus.OK);
    }

    @GetMapping("/repair/{id}")
    public ResponseEntity<Repair> getRepairById(@PathVariable("id") Long id) {
        Repair d = this.repairService.getRepairById(id);
        return new ResponseEntity<Repair>(this.repairService.getRepairById(id), HttpStatus.OK);
    }

    @PostMapping("/repair/addOrUpdate")
    @ResponseStatus(HttpStatus.OK)
    public void addOrUpdate(@RequestBody RepairDTO c){
        Repair r = this.repairService.convertDtoToEntity(c);
        this.repairService.addOrUpdate(r);
    }

    @DeleteMapping("/repair/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRepair(@PathVariable(value = "id") long id) {
        this.repairService.deleteRepair(id);
    }
}
