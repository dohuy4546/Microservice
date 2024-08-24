package com.husony.location_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDTO {
    private Long id;
    private String name;
    private String image;
    private String descriptions;
    private Date boughtDate;
    private String status;
    private CategoryDTO categoryId;
    private ManufacturerDTO manufacturerId;
}
