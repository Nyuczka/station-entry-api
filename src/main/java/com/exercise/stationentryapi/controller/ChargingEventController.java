package com.exercise.stationentryapi.controller;

import com.exercise.stationentryapi.model.ChargingEvent;
import com.exercise.stationentryapi.model.dto.ChargingEventDTO;
import com.exercise.stationentryapi.service.ChargingEventsService;
import com.exercise.stationentryapi.wrapper.ResponseWrapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/charging-events", produces = "application/json")
public class ChargingEventController {

    private final ChargingEventsService chargingEventsService;

    public ChargingEventController(ChargingEventsService chargingEventsService) {
        this.chargingEventsService = chargingEventsService;
    }


    @PostMapping(value="/add-entry", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<?> addEntry(@RequestBody ChargingEventDTO chargingEventDTO){
        Optional<ChargingEvent> chargingEvent = chargingEventsService.addChargingEvent(chargingEventDTO);
        if(chargingEvent.isPresent()){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping
    public ResponseEntity<?>
    getChargingEventsByStationName(@RequestParam final String stationName,
                                   @RequestParam(name= "limit", defaultValue = "0") final int limit) {
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
        return new ResponseEntity<>(chargingEventsService.getAverageEnergy(), HttpStatus.OK);
    }

    @GetMapping("/max-energy")
    public ResponseEntity<?>
    getUsersWithMaxEnergyForDaysBetween(@RequestParam @DateTimeFormat(pattern = "yyyy-M-d") final LocalDate startDate,
                                        @RequestParam @DateTimeFormat(pattern = "yyyy-M-d") final LocalDate endDate) {
        ResponseWrapper wrapper = new ResponseWrapper(chargingEventsService.getUsersWithMaxEnergyForDateBetween(startDate, endDate));
        return new ResponseEntity<>(wrapper, HttpStatus.OK);
    }
}
