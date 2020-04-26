package com.doncorp.innovsolvtechofflineassessment.repository;

import com.doncorp.innovsolvtechofflineassessment.model.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Integer> {
}
