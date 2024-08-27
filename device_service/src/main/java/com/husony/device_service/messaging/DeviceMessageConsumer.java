package com.husony.device_service.messaging;

import com.husony.device_service.pojo.Device;
import com.husony.device_service.service.DeviceService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DeviceMessageConsumer {
    @Autowired
    private DeviceService deviceService;

    @RabbitListener(queues = "deviceQueue")
    public void consumeMessage(DeviceMessage l){
        System.out.println(l);
        System.out.println("Đã lắng nghe");
        Device d = this.deviceService.getDeviceById(l.getId());
        d.setStatus(l.getStatus());
        this.deviceService.addOrUpdate(d);
    }
}
