package com.husony.location_service.controller;

import com.husony.location_service.client.DeviceClient;
import com.husony.location_service.dto.DeviceDTO;
import com.husony.location_service.dto.LocationHistoryDTO;
import com.husony.location_service.pojo.Location;
import com.husony.location_service.pojo.LocationHistory;
import com.husony.location_service.service.LocationHistoryService;
import com.husony.location_service.service.LocationService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/locationservice")
public class ApiLocationHistoryController {
    @Autowired
    private LocationHistoryService locationHistoryService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private DeviceClient deviceClient;

    @GetMapping("/locationhistory")
    public ResponseEntity<List<LocationHistory>> getLocation(@RequestParam Map<String, String> params) {
        return new ResponseEntity<List<LocationHistory>>(this.locationHistoryService.getLocationHistory(params), HttpStatus.OK);
    }

    @PostMapping("/locationhistory/addOrUpdate")
    @ResponseStatus(HttpStatus.OK)
    public void addOrUpdate(@RequestBody LocationHistoryDTO l, BindingResult rs){
        if (rs.hasErrors()){
            System.out.println(rs);
        }
        LocationHistory location = new LocationHistory();
        BeanUtils.copyProperties(l,location);
        Date now = new Date();
        location.setLocationId(this.locationService.getLocationById(Long.parseLong(l.getLocationId())));
        location.setBeginDate(now);
        location.setActive(true);

        this.locationHistoryService.addOrUpdate(location);
    }

    @DeleteMapping("/locationhistory/delete/{locationHistoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLocation(@PathVariable(value = "locationHistoryId") long id) {
        this.locationHistoryService.deleteLocationHistory(id);
    }

    @GetMapping("/location/{id}/device")
    @RateLimiter(name = "deviceBreaker", fallbackMethod = "deviceBreakerFallBack")
    public ResponseEntity<List<DeviceDTO>> getDevice(@PathVariable("id") Long id){
        Map<String, String> params = new HashMap<>();
        params.put("locationId", String.valueOf(id));
        params.put("active", "true");
        List<LocationHistory> list = this.locationHistoryService.getLocationHistory(params);
        List<DeviceDTO> device = new ArrayList<>();;
        for (LocationHistory l : list) {
            Long deviceId = l.getDevice();
            DeviceDTO d = deviceClient.getDeviceById(id);
            if (d != null) {
                device.add(d);
            }
        }
        return new ResponseEntity<List<DeviceDTO>>(device, HttpStatus.OK);
    }

    public ResponseEntity<String> deviceBreakerFallBack(){
        return new ResponseEntity<>("Not working", HttpStatus.NOT_FOUND);
    }
}
