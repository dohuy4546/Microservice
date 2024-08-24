package com.husony.device_service.controller;

import com.husony.device_service.pojo.Category;
import com.husony.device_service.pojo.Manufacturer;
import com.husony.device_service.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/api")
public class ApiManufacturerController {
    @Autowired
    private ManufacturerService manufacturerService;

    @GetMapping("/manufacturer")
    public ResponseEntity<List<Manufacturer>> getManufacturer(@RequestParam Map<String, String> params) {
        return new ResponseEntity<List<Manufacturer>>(this.manufacturerService.getManufacturer(params), HttpStatus.OK);
    }

    @PostMapping("/manufacturer/addOrUpdate")
    @ResponseStatus(HttpStatus.OK)
    public void addOrUpdate(@RequestBody Manufacturer m){
        System.out.println(m);
        this.manufacturerService.addOrUpdate(m);
    }

    @DeleteMapping("/manufacturer/delete/{manufacturerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "manufacturerId") long id) {
        this.manufacturerService.deleteManufacturer(id);
    }
}
