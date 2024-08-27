package com.husony.maintenance_repair_service.dto;

import com.husony.maintenance_repair_service.pojo.RepairType;
import com.husony.maintenance_repair_service.pojo.Report;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepairDTO {
    private Long id;
    private String name;
    private Date date;
    private Double cost;
    private Long repairTypeId;
    private Long reportId;
}
