package com.exercise.stationentryapi.repository.events.impl;

import com.exercise.stationentryapi.model.ChargingEvent;
import com.exercise.stationentryapi.repository.events.APIChargingEventRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Component
public class ChargingEventRepositoryImpl implements APIChargingEventRepository {

    private static final String TOP_RECORDS_FOR_STATION_QUERY =
            "SELECT * FROM (SELECT station_name, max(end_date) FROM charging_event GROUP BY station_name) " +
                    "AS latest_records INNER JOIN charging_event ON " +
                    "latest_records.station_name = charging_event.station_name WHERE " +
                    "charging_event.station_name =:name ORDER BY charging_event.end_date desc LIMIT :limit";

    private static final String MAX_ENERGY_PER_DAY_QUERY= "SELECT user_id AS userID, DATE(end_date) AS date FROM " +
            "((SELECT DATE(end_date) AS date, MAX(energy) as energy FROM charging_event WHERE DATE(end_date) " +
            "BETWEEN :startDate AND :endDate GROUP BY DATE(end_date)) AS energy_per_day) INNER JOIN " +
            "charging_event ON DATE(charging_event.end_date) = energy_per_day.date AND " +
            "charging_event.energy = energy_per_day.energy";


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ChargingEvent> findTopNEventsForStation(String stationName, int limit) {
        Query nativeQuery = entityManager.createNativeQuery(
                TOP_RECORDS_FOR_STATION_QUERY, ChargingEvent.class);
        nativeQuery.setParameter("name", stationName);
        nativeQuery.setParameter("limit", limit);
        return nativeQuery.getResultList();

    }

    @Override
    public List<Object[]> findUsersWithMaxEnergyForDayBetween(LocalDate startDate, LocalDate endDate) {
        Query nativeQuery = entityManager.createNativeQuery(
                MAX_ENERGY_PER_DAY_QUERY);
        nativeQuery.setParameter("startDate", startDate);
        nativeQuery.setParameter("endDate", endDate);

        return nativeQuery.getResultList();
    }

}
