package org.dikiwhy.parking.system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date entryTime;
    private Date exitTime;
    private Long longParkingTime;
    private Integer totalPayment;
    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL)
    private Ticket ticket;
}
