package com.booking2.service;

import com.booking2.payload.ScheduleDTO;

public interface ScheduleService {
   // Schedule createSchedule(Schedule schedule);
    ScheduleDTO createSchedule(long busId, long routeId, ScheduleDTO schedulesDTO);
}
