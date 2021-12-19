package com.exercise.stationentryapi.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChargingEventDTO {

    @JsonProperty(value = "Plug in event ID")
    private BigInteger pluginEventID;

    @JsonProperty(value = "Start Date")
    private String startDate;

    @JsonProperty(value = "End Date")
    private String endDate;

    @JsonProperty(value = "Total Duration")
    private String totalDuration;

    @JsonProperty(value = "Charging Time")
    private String chargingTime;

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
    private String transactionDate;

    @JsonProperty(value = "Fee")
    private double fee;

    @JsonProperty(value = "Currency")
    private String currency;

}
