package com.exercise.stationentryapi.controller;

import com.exercise.stationentryapi.model.ChargingEvent;
import com.exercise.stationentryapi.model.UsersAverageEnergyProjection;
import com.exercise.stationentryapi.repository.events.ChargingEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/charging-events")
public class ChargingEventController {

    @Autowired
    private ChargingEventRepository chargingEventRepository;


    @GetMapping("/all")
    public ResponseEntity<List<ChargingEvent>> getAllEvents() {
        return ResponseEntity.ok(chargingEventRepository.findAll());
    }

    @GetMapping
    public ResponseEntity<List<ChargingEvent>> getChargingEventsByStationName(@RequestParam final String stationName,
                                                                              @RequestParam(name = "limit", defaultValue = "0") final int limit) {
        return ResponseEntity.ok(chargingEventRepository.findTopNEventsForStation(stationName, limit));
    }

    @GetMapping("/{userID}")
    public ResponseEntity<List<ChargingEvent>> getChargingEventsForUserIDAndEnergyConsumptionRange
            (@PathVariable final String userID, @RequestParam("minEnergy") @Min(0) final double minEnergy,
             @RequestParam("maxEnergy") @Min(0) final double maxEnergy) {
        return ResponseEntity.ok(chargingEventRepository.findChargingEventsByUserIDAndEnergyBetween(userID, minEnergy, maxEnergy));
    }

    @GetMapping("/average-energy-per-user")
    public ResponseEntity<List<UsersAverageEnergyProjection>> getAverageEnergyConsumption() {
        List<UsersAverageEnergyProjection> averageEnergyConsumption = chargingEventRepository.findAverageEnergyConsumptionPerUser();
        return ResponseEntity.ok(averageEnergyConsumption);
    }

    @GetMapping("/average-energy")
    public ResponseEntity<Double> getAverageEnergy() {
        return ResponseEntity.ok(chargingEventRepository.findAverageEnergy());
    }

    @GetMapping("/max-energy")
    public ResponseEntity<List<Object[]>>
    getUsersWithMaxEnergyForDaysBetween(@RequestParam @DateTimeFormat(pattern = "yyyy-M-d") final LocalDate startDate,
                                        @RequestParam @DateTimeFormat(pattern = "yyyy-M-d") final LocalDate endDate) {
        return ResponseEntity.ok(chargingEventRepository.findUsersWithMaxEnergyForDayBetween(startDate, endDate));
    }
}
