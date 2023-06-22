package com.booking2.service;

import com.booking2.payload.BusDTO;

public interface BusService {
    //Bus createBus(Bus bus);
    BusDTO createBus(long busOperatorId, BusDTO busDTO);

}
