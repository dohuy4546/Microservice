package com.husony.device_service.service;

import com.husony.device_service.pojo.Device;

import java.util.List;
import java.util.Map;

public interface DeviceService {
    List<Device> getDevices(Map<String, String> params);
    void addOrUpdate(Device d);
    Device getDeviceById(long id);
    void deleteDevice(long id);
}
