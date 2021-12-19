package com.exercise.stationentryapi.model;

import org.springframework.beans.factory.annotation.Value;

public interface UsersAverageEnergyProjection {

    @Value("#{target.userID}")
    String getUserID();

    @Value("#{target.average}")
    Double getAverageEnergy();
}
