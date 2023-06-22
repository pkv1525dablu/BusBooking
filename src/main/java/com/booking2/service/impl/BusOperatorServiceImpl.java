package com.booking2.service.impl;


import com.booking2.entities.BusOperator;
import com.booking2.payload.BusOperatorDTO;
import com.booking2.repository.BusOperatorRepository;
import com.booking2.service.BusOperatorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BusOperatorServiceImpl implements BusOperatorService {
    @Autowired
    private BusOperatorRepository busOperatorRepository;
    @Autowired
    private ModelMapper modelMapper;



    // Model Mapper use
    @Override
    public BusOperatorDTO createBusOperator(BusOperatorDTO busOperatorDTO) {
        BusOperator busOperator = mapToEntity(busOperatorDTO);
        busOperator.setCreatedAt(new Date());
        busOperator.setUpdatedAt(new Date());
        busOperatorRepository.save(busOperator);
        return mapToDto(busOperator);
    }

    BusOperator mapToEntity(BusOperatorDTO busOperatorDTO) {
        return modelMapper.map(busOperatorDTO, BusOperator.class);
    }

    BusOperatorDTO mapToDto(BusOperator busOperator) {
        return modelMapper.map(busOperator, BusOperatorDTO.class);
    }
}
