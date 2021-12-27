package com.exercise.stationentryapi.mapper;

import com.exercise.stationentryapi.model.ChargingEvent;
import com.exercise.stationentryapi.model.dto.ChargingEventDTO;
import com.exercise.stationentryapi.model.dto.StationDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class ChargingEventMapper {

//    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//
//    public ChargingEventDTO toDTO(ChargingEvent chargingEvent){
//        ChargingEventDTO chargingEventDTO = new ChargingEventDTO();
//        chargingEventDTO.setPluginEventID(chargingEvent.getPluginEventID());
//        chargingEventDTO.setStartDate(chargingEvent.getStartDate().format(dateTimeFormatter));
//        chargingEventDTO.setEndDate(chargingEvent.getEndDate().format(dateTimeFormatter));
//        chargingEventDTO.setChargingTime(chargingEvent.getChargingTime().format(timeFormatter));
//        chargingEventDTO.setTotalDuration(chargingEvent.getTotalDuration().format(timeFormatter));
//        chargingEventDTO.setEnergy(chargingEvent.getEnergy());
//        chargingEventDTO.setGhgSavings(chargingEvent.getGhgSavings());
//        chargingEventDTO.setGasolineSavings(chargingEvent.getGasolineSavings());
//        chargingEventDTO.setEndedBy(chargingEvent.getEndedBy());
//        chargingEventDTO.setUserID(chargingEvent.getUserID());
//        chargingEventDTO.setTransactionDate(chargingEvent.getTransactionDate().format(dateTimeFormatter));
//        chargingEventDTO.setCurrency(chargingEvent.getCurrency());
//        chargingEventDTO.setFee(chargingEvent.getFee());
//        chargingEventDTO.setStation(chargingEvent.getStation());
//        return chargingEventDTO;
//    }
//
//    public ChargingEvent toEntity(ChargingEventDTO chargingEventDTO){
//        ChargingEvent chargingEvent = new ChargingEvent();
//        chargingEvent.setPluginEventID(chargingEventDTO.getPluginEventID());
//        chargingEvent.setStartDate(LocalDateTime.parse(chargingEventDTO.getStartDate(), dateTimeFormatter));
//        chargingEvent.setEndDate(LocalDateTime.parse(chargingEventDTO.getEndDate(), dateTimeFormatter));
//        chargingEvent.setChargingTime(LocalTime.parse(chargingEventDTO.getChargingTime(), timeFormatter));
//        chargingEvent.setTotalDuration(LocalTime.parse(chargingEventDTO.getTotalDuration(), timeFormatter));
//        chargingEvent.setEnergy(chargingEventDTO.getEnergy());
//        chargingEvent.setGhgSavings(chargingEventDTO.getGhgSavings());
//        chargingEvent.setGasolineSavings(chargingEventDTO.getGasolineSavings());
//        chargingEvent.setUserID(chargingEventDTO.getUserID());
//        chargingEvent.setEndedBy(chargingEventDTO.getEndedBy());
//        chargingEvent.setTransactionDate(LocalDateTime.parse(chargingEventDTO.getTransactionDate(), dateTimeFormatter));
//        chargingEvent.setCurrency(chargingEventDTO.getCurrency());
//        chargingEvent.setFee(chargingEventDTO.getFee());
//        chargingEvent.setStation(chargingEventDTO.getStation());
//        return chargingEvent;
//    }
}
