package com.exercise.stationentryapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "station")
public class Station {

    @Id
    private String name;

    @Column(name = "mac_address", columnDefinition = "VARCHAR(20) default 'N/A'", nullable = false)
    private String macAddress;

    @Column(name = "evse_id")
    private String evseID;

//    @Column
//    private String systemSN;
//
//    private String modelNumber;

    @Column(name = "org_name", columnDefinition = "varchar(100) default 'City of Palo Alto'")
    private String organizationName;
}
