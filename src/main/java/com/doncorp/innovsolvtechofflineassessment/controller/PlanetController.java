package com.doncorp.innovsolvtechofflineassessment.controller;

import com.doncorp.innovsolvtechofflineassessment.exception.ResourceNotFoundException;
import com.doncorp.innovsolvtechofflineassessment.model.Planet;
import com.doncorp.innovsolvtechofflineassessment.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planets")
public class PlanetController {
private static final String PLANET_NOT_FOUND_MSG="Planet not found with id :";
    @Autowired
    private PlanetRepository planetRepository;

    // get all planets
    @GetMapping
    public List<Planet> getAllPlanets() {
        return this.planetRepository.findAll();
    }

    // get planet by id
    @GetMapping("/{id}")
    public Planet getPlanetById(@PathVariable(value = "id") int planetId) {
        return this.planetRepository.findById(planetId)
                .orElseThrow(() -> new ResourceNotFoundException(PLANET_NOT_FOUND_MSG + planetId));
    }

    // create planet
    @PostMapping
    public Planet createPlanet(@RequestBody Planet planet) {
        return this.planetRepository.save(planet);
    }

    // update planet
    @PutMapping("/{id}")
    public Planet updatePlanet(@RequestBody Planet planet, @PathVariable ("id") int planetId) {
        Planet existingPlanet = this.planetRepository.findById(planetId)
                .orElseThrow(() -> new ResourceNotFoundException(PLANET_NOT_FOUND_MSG + planetId));
        existingPlanet.setPlanetNode(planet.getPlanetNode());
        existingPlanet.setPlanetName(planet.getPlanetName());
        return this.planetRepository.save(existingPlanet);
    }

    // delete planet by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Planet> deletePlanet(@PathVariable ("id") int planetId){
        Planet existingPlanet = this.planetRepository.findById(planetId)
                .orElseThrow(() -> new ResourceNotFoundException(PLANET_NOT_FOUND_MSG+ planetId));
        this.planetRepository.delete(existingPlanet);
        return ResponseEntity.ok().build();
    }
}
