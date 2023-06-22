package com.booking2.service.impl;


import com.booking2.entities.Route;
import com.booking2.payload.RouteDTO;
import com.booking2.repository.RouteRepository;
import com.booking2.service.RouteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Date;

@Service
public class RouteServiceImpl implements RouteService {
    @Autowired

    private RouteRepository routeRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RouteDTO createRoute(RouteDTO routeDTO) {
            Route route = mapToEntity(routeDTO);
            route.setCreatedAt(new Date());
            route.setUpdatedAt(new Date());
            Route save = routeRepository.save(route);
            return mapToDto(save);
        }
        Route mapToEntity (RouteDTO routeDTO){
            return modelMapper.map(routeDTO, Route.class);
        }
        RouteDTO mapToDto (Route route){
            return modelMapper.map(route, RouteDTO.class);
        }
    }