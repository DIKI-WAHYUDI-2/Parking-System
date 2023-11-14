package org.dikiwhy.parking.system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @Column(length = 20, nullable = false)
    private String numberPlate;

    @Column(length = 20, nullable = false)
    private String typeVehicle;

    @Column(nullable = false)
    private Date entryTime;

    @Column(length = 20, nullable = false)
    private String status;

    private Date exitTime;

    private Long longParkingTime;

    private Integer parkingFee;

}
