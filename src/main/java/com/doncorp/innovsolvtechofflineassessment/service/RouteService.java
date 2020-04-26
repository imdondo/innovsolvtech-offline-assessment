package com.doncorp.innovsolvtechofflineassessment.service;

import com.doncorp.innovsolvtechofflineassessment.model.Route;

import java.util.List;

public interface RouteService {
    Route save(Route route);
    List<Route> findAll();
    Route findOne(String name);
    Route findById(int id);
    Route update(Route route);
    void delete(int id);
}
