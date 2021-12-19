package com.exercise.stationentryapi.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResponseWrapper {
    @JsonProperty("data")
    private List<?> data;

    public ResponseWrapper(List<?> data) {
        this.data = data;
    }

}
