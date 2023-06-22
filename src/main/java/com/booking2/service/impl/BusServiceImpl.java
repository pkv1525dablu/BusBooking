package com.booking2.service.impl;


import com.booking2.entities.Bus;
import com.booking2.entities.BusOperator;
import com.booking2.payload.BusDTO;
import com.booking2.payload.BusOperatorDTO;
import com.booking2.repository.BusOperatorRepository;
import com.booking2.repository.BusRepository;
import com.booking2.service.BusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BusServiceImpl implements BusService {
    @Autowired
    private BusRepository busRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BusOperatorRepository busOperatorRepository;

    @Override
    public BusDTO createBus(long busOperatorId, BusDTO busDTO) {
        BusOperator busOperatorid = busOperatorRepository.findById(busOperatorId)
                .orElseThrow(() -> new RuntimeException("Bus Operator Id Not found"));
        Bus bus = mapToEntity(busDTO);
        bus.setCreatedAt(new Date());
        bus.setUpdatedAt(new Date());
        bus.setOperator(busOperatorid);
        Bus save = busRepository.save(bus);
        return mapToDto(save);
    }

    Bus mapToEntity(BusDTO busDTO) {
        return modelMapper.map(busDTO, Bus.class);
    }

    BusDTO mapToDto(Bus bus) {
        return modelMapper.map(bus, BusDTO.class);
    }
}
