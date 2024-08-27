package com.husony.device_service.controller;

import com.husony.device_service.dto.LocationHistoryMessage;
import com.husony.device_service.messaging.LocationHistoryMessageProducer;
import com.husony.device_service.pojo.Category;
import com.husony.device_service.pojo.Device;
import com.husony.device_service.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/deviceservice")
public class ApiDeviceController {
    @Autowired
    private DeviceService deviceService;


    @GetMapping("/device")
    public ResponseEntity<List<Device>> getDevices(@RequestParam Map<String, String> params,
                                                   @RequestHeader("username") String username,
                                                   @RequestHeader("user_role") String role) {
        System.out.println(username);
        return new ResponseEntity<List<Device>>(this.deviceService.getDevices(params), HttpStatus.OK);
    }

    @GetMapping("/device/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable("id") Long id) {
        System.out.println(id);
        Device d = this.deviceService.getDeviceById(id);
        System.out.println(d);
        return new ResponseEntity<Device>(this.deviceService.getDeviceById(id), HttpStatus.OK);
    }

    @PostMapping("/device/addOrUpdate")
    public void addOrUpdate(@ModelAttribute @Validated Device c, BindingResult rs){
        if (rs.hasErrors()){
            System.out.println("Lỗi rồi");
            System.out.println(rs);
        }
        System.out.println(c);
        this.deviceService.addOrUpdate(c);
    }
}
