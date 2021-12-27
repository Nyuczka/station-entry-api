package com.exercise.stationentryapi.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StationDTO implements Serializable {
    @JsonProperty("Evse ID")
    private String evseID;

    @JsonProperty("MAC Address")
    private String macAddress;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Organization Name")
    private String orgName;

}
