package com.exercise.stationentryapi.service;

import com.exercise.stationentryapi.model.ChargingEvent;
import com.exercise.stationentryapi.model.dto.ChargingEventDTO;
import com.exercise.stationentryapi.model.dto.StationDTO;
import com.exercise.stationentryapi.model.dto.UserEnergy;
import com.exercise.stationentryapi.model.dto.UserToDate;
import com.exercise.stationentryapi.repository.events.ChargingEventRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ChargingEventsService {

    private ChargingEventRepository repository;


    private ModelMapper modelMapper;

    public ChargingEventsService(ChargingEventRepository repository) {
        this.repository = repository;
        this.modelMapper = new ModelMapper();
    }

    public List<ChargingEventDTO> getChargingEvents() {
        return repository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<ChargingEventDTO> getTopNEventsForStation(String stationName, int limit){
        return repository.findTopNEventsForStation(stationName, limit).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<ChargingEventDTO> getChargingEventsForUserIDAndEnergyConsumptionRange(String userID, double minEnergy, double maxEnergy){
        return repository.findChargingEventsByUserIDAndEnergyBetween(userID, minEnergy, maxEnergy).stream().map(this::convertToDTO).collect(Collectors.toList());
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

    public Optional<ChargingEvent> addChargingEvent(ChargingEventDTO chargingEventDTO){
//        ChargingEvent chargingEvent = chargingEventMapper.toEntity(chargingEventDTO);
//        return Optional.of(repository.save(chargingEvent));
        return null;
    }

    private ChargingEventDTO convertToDTO(ChargingEvent event) {
        try {
            ChargingEventDTO dto = modelMapper.map(event, ChargingEventDTO.class);
            StationDTO stationDTO = modelMapper.map(event.getStation(), StationDTO.class);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            dto.setStartDate(dateFormat.parse(dateFormat.format(event.getStartDate())));
            dto.setEndDate(dateFormat.parse(dateFormat.format(event.getEndDate())));
            dto.setTransactionDate(dateFormat.parse(dateFormat.format(event.getTransactionDate())));
            dto.setStation(stationDTO);
            return dto;
        } catch (ParseException exception){
            exception.printStackTrace();
        }
        return null;
    }


}
