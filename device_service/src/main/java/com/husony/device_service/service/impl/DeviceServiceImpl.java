package com.husony.device_service.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.husony.device_service.dto.LocationHistoryMessage;
import com.husony.device_service.messaging.LocationHistoryMessageProducer;
import com.husony.device_service.pojo.Device;
import com.husony.device_service.repository.DeviceRepository;
import com.husony.device_service.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private LocationHistoryMessageProducer locationHistoryMessageProducer;

    @Override
    public List<Device> getDevices(Map<String, String> params) {
        return this.deviceRepository.getDevices(params);
    }

    @Override
    public void addOrUpdate(Device d) {
        if (d.getFile() != null) {
            if (!d.getFile().isEmpty()) {
                try {
                    Map res = this.cloudinary.uploader().upload(d.getFile().getBytes(),
                            ObjectUtils.asMap("resource_type", "auto"));

                    d.setImage(res.get("secure_url").toString());
                } catch (IOException ex) {
                    Logger.getLogger(DeviceServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        this.deviceRepository.addOrUpdate(d);
        LocationHistoryMessage locationHistoryMessage = new LocationHistoryMessage();
        locationHistoryMessage.setLocationId(d.getLocationId());
        locationHistoryMessage.setDevice(d.getId());
        locationHistoryMessageProducer.sendMessage(locationHistoryMessage);
    }

    @Override
    public Device getDeviceById(long id) {
        return this.deviceRepository.getDeviceById(id);
    }

    @Override
    public void deleteDevice(long id) {
        this.deviceRepository.deleteDevice(id);
    }

}
