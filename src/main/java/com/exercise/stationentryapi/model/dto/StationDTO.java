package com.exercise.stationentryapi.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StationDTO {
    @JsonProperty("Evse ID")
    private String evseID;

    @JsonProperty("MAC Address")
    private String macAddress;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Organization Name")
    private String orgName;

}
