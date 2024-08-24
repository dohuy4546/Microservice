package com.husony.location_service.messaging;

import com.husony.location_service.dto.LocationHistoryDTO;
import com.husony.location_service.pojo.LocationHistory;
import com.husony.location_service.service.LocationHistoryService;
import com.husony.location_service.service.LocationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LocationHistoryMessageConsumer {
    @Autowired
    private LocationHistoryService locationHistoryService;

    @Autowired
    private LocationService locationService;

    @RabbitListener(queues = "locationHistoryQueue")
    public void consumeMessage(LocationHistoryDTO l){
        System.out.println(l);
        System.out.println("Đã lắng nghe");
        LocationHistory location = new LocationHistory();
        BeanUtils.copyProperties(l,location);
        Date now = new Date();
        location.setLocationId(this.locationService.getLocationById(Long.parseLong(l.getLocationId())));
        location.setBeginDate(now);
        location.setActive(true);
        System.out.println(location);
        locationHistoryService.addOrUpdate(location);
    }
}
