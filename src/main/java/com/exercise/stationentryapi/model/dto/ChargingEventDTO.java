package com.exercise.stationentryapi.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChargingEventDTO implements Serializable {

    @JsonIgnore
    private Integer id;

    @JsonProperty(value = "Plug in event ID")
    private BigInteger pluginEventID;

    @JsonProperty(value = "Start Date")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date startDate;

    @JsonProperty(value = "End Date")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date endDate;

    @JsonProperty(value = "Total Duration")
    private LocalTime totalDuration;

    @JsonProperty(value = "Charging Time")
    private LocalTime chargingTime;

    @JsonProperty(value = "Energy")
    private double energy;

    @JsonProperty(value = "GHG Savings")
    private double ghgSavings;

    @JsonProperty(value = "Gasoline Savings")
    private double gasolineSavings;

    @JsonProperty(value = "Ended By")
    private String endedBy;

    @JsonProperty(value = "User ID")
    private String userID;

    @JsonProperty(value = "Transaction Date")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date transactionDate;

    @JsonProperty(value = "Fee")
    private double fee;

    @JsonProperty(value = "Currency")
    private String currency;

    @JsonProperty(value = "station")
    private StationDTO station;

}
