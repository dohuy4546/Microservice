package com.husony.maintenance_repair_service.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "Maintenance_device")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "maintenance_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Maintenance maintenanceId;

    @NotNull
    @Column(name = "device")
    private Long deviceId;
}
