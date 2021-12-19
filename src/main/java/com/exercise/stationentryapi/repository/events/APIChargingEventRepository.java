package com.exercise.stationentryapi.repository.events;

import com.exercise.stationentryapi.model.ChargingEvent;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface APIChargingEventRepository {

    List<ChargingEvent> findTopNEventsForStation(String stationName, int limit);

    List<String[]> findUsersWithMaxEnergyForDayBetween(LocalDate startDate, LocalDate endDate);

}
