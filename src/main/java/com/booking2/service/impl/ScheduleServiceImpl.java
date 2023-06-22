package com.booking2.service.impl;


import com.booking2.entities.Bus;
import com.booking2.entities.Route;
import com.booking2.entities.Schedule;
import com.booking2.payload.ScheduleDTO;
import com.booking2.repository.BusRepository;
import com.booking2.repository.RouteRepository;
import com.booking2.repository.ScheduleRepository;
import com.booking2.service.ScheduleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private BusRepository busRepository;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ScheduleDTO createSchedule(long busId, long routeId, ScheduleDTO schedulesDTO) {
        Bus busid = busRepository.findById(busId).orElseThrow(() -> new RuntimeException("Bus Id NotFound"));
        Route routeid = routeRepository.findById(routeId).orElseThrow(() -> new RuntimeException("Route Id not found"));
        Schedule schedule = mapToEntity(schedulesDTO);
        schedule.setCreatedAt(new Date());
        schedule.setUpdatedAt(new Date());
        schedule.setBus(busid);
        schedule.setRoute(routeid);
        Schedule save = scheduleRepository.save(schedule);
        return mapToDto(save);
    }

    Schedule mapToEntity(ScheduleDTO schedulesDTO) {
        return modelMapper.map(schedulesDTO, Schedule.class);
    }

    ScheduleDTO mapToDto(Schedule schedule) {
        return modelMapper.map(schedule, ScheduleDTO.class);
    }
}