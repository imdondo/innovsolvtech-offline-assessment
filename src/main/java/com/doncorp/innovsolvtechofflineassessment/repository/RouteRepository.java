package com.doncorp.innovsolvtechofflineassessment.repository;

import com.doncorp.innovsolvtechofflineassessment.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {
}
