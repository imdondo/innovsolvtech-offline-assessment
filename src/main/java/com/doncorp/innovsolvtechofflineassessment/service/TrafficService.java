package com.doncorp.innovsolvtechofflineassessment.service;

import com.doncorp.innovsolvtechofflineassessment.model.Traffic;

import java.util.List;

public interface TrafficService {
    Traffic save(Traffic traffic);
    List<Traffic> findAll();
    Traffic findOne(String name);
    Traffic findById(int id);
    Traffic update(Traffic traffic);
    void delete(int id);
}
