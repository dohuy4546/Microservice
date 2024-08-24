package com.husony.device_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationHistoryMessage {
    private Long id;
    private Long device;
    private Long locationId;
}
