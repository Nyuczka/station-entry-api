package com.exercise.stationentryapi.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEnergy {

    @JsonProperty("User ID")
    private String userID;

    @JsonProperty("Energy")
    private String energy;
}
