package com.doncorp.innovsolvtechofflineassessment.service;

import com.doncorp.innovsolvtechofflineassessment.model.Planet;

import java.util.List;

public interface PlanetService {
    Planet save(Planet planet);
    List<Planet> findAll();
    Planet findOne(String name);
    Planet findById(int id);
    Planet update(Planet planet);
    void delete(int id);
}
