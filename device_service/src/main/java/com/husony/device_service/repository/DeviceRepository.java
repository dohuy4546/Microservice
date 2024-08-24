package com.husony.device_service.repository;

import com.husony.device_service.pojo.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DeviceRepository {
    List<Device> getDevices(Map<String, String> params);
    void addOrUpdate(Device d);
    Device getDeviceById(Long id);
    void deleteDevice(Long id);
}
