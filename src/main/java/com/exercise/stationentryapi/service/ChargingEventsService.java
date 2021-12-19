package com.exercise.stationentryapi.service;

import com.exercise.stationentryapi.mapper.ChargingEventMapper;
import com.exercise.stationentryapi.model.ChargingEvent;
import com.exercise.stationentryapi.model.dto.ChargingEventDTO;
import com.exercise.stationentryapi.model.dto.UserEnergy;
import com.exercise.stationentryapi.model.dto.UserToDate;
import com.exercise.stationentryapi.repository.events.ChargingEventRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ChargingEventsService {

    private ChargingEventRepository repository;

    private ChargingEventMapper chargingEventMapper;

    public ChargingEventsService(ChargingEventRepository repository, ChargingEventMapper chargingEventMapper) {
        this.repository = repository;
        this.chargingEventMapper = chargingEventMapper;
    }

    public List<ChargingEventDTO> getChargingEvents() {
        return convertEntityToDTO(repository.findAll());
    }

    public List<ChargingEventDTO> getTopNEventsForStation(String stationName, int limit){
        return convertEntityToDTO(repository.findTopNEventsForStation(stationName, limit));
    }

    public List<ChargingEventDTO> getChargingEventsForUserIDAndEnergyConsumptionRange(String userID, double minEnergy, double maxEnergy){
        return convertEntityToDTO(repository.findChargingEventsByUserIDAndEnergyBetween(userID, minEnergy, maxEnergy));
    }

    public List<UserEnergy> getAverageEnergyConsumptionPerUser(){
        List<String[]> averageEnergyConsumptionPerUser = repository.findAverageEnergyConsumptionPerUser();
        List<UserEnergy> users = new ArrayList<>();
        if(!averageEnergyConsumptionPerUser.isEmpty()){
            averageEnergyConsumptionPerUser.forEach(value -> users.add(new UserEnergy(value[0], value[1])));
        }
        return users;
    }

    public Map<String, Double> getAverageEnergy(){
        Map<String, Double> averageEnergy = new HashMap<>();
        averageEnergy.put("Average Energy", repository.findAverageEnergy());
        return averageEnergy;
    }

    public List<UserToDate> getUsersWithMaxEnergyForDateBetween(LocalDate startDate, LocalDate endDate){
        List<String[]> usersWithMaxEnergyForDayBetween = repository.findUsersWithMaxEnergyForDayBetween(startDate, endDate);
        List<UserToDate> usersToDates = new ArrayList<>();
        if(!usersWithMaxEnergyForDayBetween.isEmpty()){
            usersWithMaxEnergyForDayBetween.forEach(value -> usersToDates.add(new UserToDate(value[0], value[1])));
        }
        return usersToDates;
    }

    private List<ChargingEventDTO> convertEntityToDTO(List<ChargingEvent> events){
        List<ChargingEventDTO> dtos = new ArrayList<>();
        for(ChargingEvent chargingEvent: events){
            dtos.add(chargingEventMapper.toDTO(chargingEvent));
        }
        return dtos;
    }

}
