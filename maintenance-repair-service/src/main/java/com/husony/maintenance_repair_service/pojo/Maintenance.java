package com.husony.maintenance_repair_service.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Maintenance")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "last_maintenance_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastMaintenanceDate;

    @NotNull
    @Column(name = "next_maintenance_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date nextMaintenanceDate;

    @NotNull
    @Column(name = "interval_month")
    private int intervalMonth;

    @JoinColumn(name = "maintenance_type_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private RepairType maintenanceTypeId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maintenanceId")
    @JsonIgnore
    private Set<MaintenanceDevice> maintenanceDeviceId;
}
