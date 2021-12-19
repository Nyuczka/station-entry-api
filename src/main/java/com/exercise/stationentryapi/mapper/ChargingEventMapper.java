package com.exercise.stationentryapi.mapper;

import com.exercise.stationentryapi.model.ChargingEvent;
import com.exercise.stationentryapi.model.dto.ChargingEventDTO;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class ChargingEventMapper {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public ChargingEventDTO toDTO(ChargingEvent chargingEvent){
        ChargingEventDTO chargingEventDTO = new ChargingEventDTO();
        chargingEventDTO.setPluginEventID(chargingEvent.getPluginEventID());
        chargingEventDTO.setStartDate(chargingEvent.getStartDate().format(dateTimeFormatter));
        chargingEventDTO.setEndDate(chargingEvent.getEndDate().format(dateTimeFormatter));
        chargingEventDTO.setChargingTime(chargingEvent.getChargingTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        chargingEventDTO.setTotalDuration(chargingEvent.getTotalDuration().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        chargingEventDTO.setEnergy(chargingEvent.getEnergy());
        chargingEventDTO.setGhgSavings(chargingEvent.getGhgSavings());
        chargingEventDTO.setGasolineSavings(chargingEvent.getGasolineSavings());
        chargingEventDTO.setEndedBy(chargingEvent.getEndedBy());
        chargingEventDTO.setUserID(chargingEvent.getUserID());
        chargingEventDTO.setTransactionDate(chargingEvent.getTransactionDate().format(dateTimeFormatter));
        chargingEventDTO.setCurrency(chargingEvent.getCurrency());
        chargingEventDTO.setFee(chargingEvent.getFee());
        return chargingEventDTO;
    }
}
