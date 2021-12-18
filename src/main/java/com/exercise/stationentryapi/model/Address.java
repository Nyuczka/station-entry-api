package com.exercise.stationentryapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String street;

    @Column
    private String county;

    @Column(columnDefinition = "VARCHAR(50) default 'California'")
    private String state;

    @Column(columnDefinition = "VARCHAR(50) default '94301'")
    private String postalCode;

    @Column(columnDefinition = "VARCHAR(50) default 'United States'")
    private String country;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_name")
    private Station station;

}
