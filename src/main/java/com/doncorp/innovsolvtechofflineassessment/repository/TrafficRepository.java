package com.doncorp.innovsolvtechofflineassessment.repository;

import com.doncorp.innovsolvtechofflineassessment.model.Route;
import com.doncorp.innovsolvtechofflineassessment.model.Traffic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TrafficRepository extends JpaRepository<Traffic, Integer> {
}
