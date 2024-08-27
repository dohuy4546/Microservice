package com.husony.maintenance_repair_service.dto;

import brave.internal.Nullable;
import com.husony.maintenance_repair_service.pojo.Employee;
import com.husony.maintenance_repair_service.pojo.Maintenance;
import com.husony.maintenance_repair_service.pojo.Repair;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDTO {
    private Long id;
    private String name;
    private String status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime updatedDate;
    private Long maintenanceId;
    private Long repairId;
    private Long employeeId;
}
