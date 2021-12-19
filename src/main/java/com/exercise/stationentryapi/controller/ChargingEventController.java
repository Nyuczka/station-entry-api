package com.exercise.stationentryapi.controller;

import com.exercise.stationentryapi.repository.events.ChargingEventRepository;
import com.exercise.stationentryapi.service.ChargingEventsService;
import com.exercise.stationentryapi.wrapper.ResponseWrapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/charging-events")
public class ChargingEventController {

    final
    ChargingEventsService chargingEventsService;

    final
    ChargingEventRepository chargingEventRepository;

    public ChargingEventController(ChargingEventsService chargingEventsService, ChargingEventRepository chargingEventRepository) {
        this.chargingEventsService = chargingEventsService;
        this.chargingEventRepository = chargingEventRepository;
    }

    @GetMapping(value = "/all", produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> getAllEvents() {
        ResponseWrapper wrapper = new ResponseWrapper(chargingEventsService.getChargingEvents());
        return new ResponseEntity<>(wrapper, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?>
    getChargingEventsByStationName(@RequestParam final String stationName,
                                   @RequestParam(name    = "limit", defaultValue = "0") final int limit) {
        ResponseWrapper wrapper = new ResponseWrapper(chargingEventsService.getTopNEventsForStation(stationName, limit));
        return new ResponseEntity<>(wrapper, HttpStatus.OK);
    }

    @GetMapping("/{userID}")
    public ResponseEntity<?> getChargingEventsForUserIDAndEnergyConsumptionRange
            (@PathVariable final String userID, @RequestParam("minEnergy") @Min(0) final double minEnergy,
             @RequestParam("maxEnergy") @Min(0) final double maxEnergy) {
        ResponseWrapper wrapper = new ResponseWrapper(chargingEventsService.getChargingEventsForUserIDAndEnergyConsumptionRange(userID, minEnergy, maxEnergy));
        return new ResponseEntity<>(wrapper, HttpStatus.OK);
    }

    @GetMapping("/average-energy-per-user")
    public ResponseEntity<?> getAverageEnergyConsumption() {
        ResponseWrapper wrapper = new ResponseWrapper(chargingEventsService.getAverageEnergyConsumptionPerUser());
        return new ResponseEntity<>(wrapper, HttpStatus.OK);
    }

    @GetMapping("/average-energy")
    public ResponseEntity<?> getAverageEnergy() {
        return ResponseEntity.ok(chargingEventsService.getAverageEnergy());
    }

    @GetMapping("/max-energy")
    public ResponseEntity<?>
    getUsersWithMaxEnergyForDaysBetween(@RequestParam @DateTimeFormat(pattern = "yyyy-M-d") final LocalDate startDate,
                                        @RequestParam @DateTimeFormat(pattern = "yyyy-M-d") final LocalDate endDate) {
        ResponseWrapper wrapper = new ResponseWrapper(chargingEventsService.getUsersWithMaxEnergyForDateBetween(startDate, endDate));
        return new ResponseEntity<>(wrapper, HttpStatus.OK);
    }
}
