package com.exercise.stationentryapi.repository.events;

import com.exercise.stationentryapi.model.ChargingEvent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChargingEventRepository extends CrudRepository<ChargingEvent, Integer>, APIChargingEventRepository {

    String AVERAGE_CONSUMED_ENERGY_PER_USER_QUERY = "SELECT event.userID as userID, avg(event.energy) as average FROM "
            + "ChargingEvent event GROUP BY event.userID";

    String AVERAGE_ENERGY_QUERY = "SELECT avg(event.energy) FROM ChargingEvent event";

    List<ChargingEvent> findAll();

    List<ChargingEvent> findChargingEventsByUserIDAndEnergyBetween(String userID, double minEnergy, double maxEnergy);

    @Query(value = AVERAGE_CONSUMED_ENERGY_PER_USER_QUERY)
    List<String[]> findAverageEnergyConsumptionPerUser();

    @Query(value = AVERAGE_ENERGY_QUERY)
    Double findAverageEnergy();

}
