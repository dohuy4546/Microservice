package com.husony.location_service.client;

import com.husony.location_service.dto.DeviceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "device-service")
public interface DeviceClient {
    @GetMapping("/api/device/{id}")
    DeviceDTO getDeviceById(@PathVariable("id") Long id);
}
