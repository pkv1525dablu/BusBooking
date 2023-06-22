package com.booking2.controller;

import com.booking2.payload.BusDTO;
import com.booking2.payload.BusOperatorDTO;
import com.booking2.payload.RouteDTO;
import com.booking2.payload.ScheduleDTO;
import com.booking2.service.BusOperatorService;
import com.booking2.service.BusService;
import com.booking2.service.RouteService;
import com.booking2.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BookingController {
    @Autowired
    private BusOperatorService busOperatorService;
    @Autowired
    private BusService busService;
    @Autowired
    private RouteService routeService;
    @Autowired
    private ScheduleService scheduleService;

    //http://localhost:8080/api/busoperators
    @PostMapping("/busoperators")
    public ResponseEntity<BusOperatorDTO> createBusOperator(@RequestBody BusOperatorDTO busOperatorDTO) {
        BusOperatorDTO busOperator =
                busOperatorService.createBusOperator(busOperatorDTO);
        return new ResponseEntity<>(busOperator, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/1/buses
    @PostMapping("/{busoperatorId}/buses")
    public ResponseEntity<BusDTO> createBus(@PathVariable long busoperatorId, @RequestBody BusDTO busDTO) {
        BusDTO bus = busService.createBus(busoperatorId, busDTO);
        return new ResponseEntity<>(bus, HttpStatus.CREATED);
    }
    //http://localhost:8080/api/routes
    @PostMapping("/routes")
    public ResponseEntity<RouteDTO> createRoute(@RequestBody RouteDTO routeDTO) {
        RouteDTO route = routeService.createRoute(routeDTO);
        return new ResponseEntity<>(route, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/1/1/schedule
    @PostMapping("/{busId}/{routeId}/schedule")
    public ResponseEntity<ScheduleDTO> createSchedule(@PathVariable long busId, @PathVariable long routeId, @RequestBody ScheduleDTO schedulesDTO) {
        ScheduleDTO schedule = scheduleService.createSchedule(busId, routeId, schedulesDTO);
        return new ResponseEntity<>(schedule, HttpStatus.CREATED);
    }
}