package com.doncorp.innovsolvtechofflineassessment.service;

import com.doncorp.innovsolvtechofflineassessment.model.Planet;
import com.doncorp.innovsolvtechofflineassessment.repository.PlanetRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlanetServiceImpl implements PlanetService {
    @Autowired
    private PlanetRepository repository;

    public PlanetServiceImpl(PlanetRepository repository) {
    }

    public List<Planet> findAll() {
        List<Planet> list = new ArrayList<>();
        repository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Planet findOne(String name) {
        return null;
    }


    @Override
    public Planet findById(int id) {
        Optional<Planet> optionalPlanet = repository.findById(id);
        return optionalPlanet.isPresent() ? optionalPlanet.get() : null;
    }


    @Override
    public Planet update(Planet planet) {
        if (planet != null) {
            BeanUtils.copyProperties(planet, planet);
            repository.save(planet);
        }
        return planet;
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }


    @Override
    public Planet save(Planet planet) {
        Planet newPlanet = new Planet();
        newPlanet.setPlanetNode(planet.getPlanetNode());
        newPlanet.setPlanetName(planet.getPlanetName());
        return repository.save(newPlanet);
    }
}
