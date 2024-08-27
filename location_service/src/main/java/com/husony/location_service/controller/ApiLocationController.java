package com.husony.location_service.controller;

import com.husony.location_service.pojo.Location;
import com.husony.location_service.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/locationservice")
public class ApiLocationController {
    @Autowired
    private LocationService locationService;


    @GetMapping("/location")
    public ResponseEntity<List<Location>> getLocation(@RequestParam Map<String, String> params) {
        return new ResponseEntity<List<Location>>(this.locationService.getLocation(params), HttpStatus.OK);
    }

    @PostMapping("/location/addOrUpdate")
    @ResponseStatus(HttpStatus.OK)
    public void addOrUpdate(@RequestBody Location l){
        System.out.println(l);
        this.locationService.addOrUpdate(l);
    }

    @DeleteMapping("/location/delete/{locationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLocation(@PathVariable(value = "locationId") long id) {
        this.locationService.deleteLocation(id);
    }


}
