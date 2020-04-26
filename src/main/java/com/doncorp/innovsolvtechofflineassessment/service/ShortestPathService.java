package com.doncorp.innovsolvtechofflineassessment.service;

import com.doncorp.innovsolvtechofflineassessment.model.Route;
import com.doncorp.innovsolvtechofflineassessment.repository.RouteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ShortestPathService {


    @Autowired
    RouteRepository routeRepository;

    public List<Route> getAllPlanetRoutes(){
        return (List<Route>) routeRepository.findAll();
    }

    public void saveRoute(Route route) {
        log.info("#### start ShortestPathService :: saveRoute #######");
        routeRepository.save(route);
    }

    public void saveorupdateRoute(Route route) {
        log.info("#### start ShortestPathService :: saveorupdateRoute #######");
        Route routeDB= routeRepository.findById(route.getRouteId()).orElse(null);;
        routeDB.setDistance(route.getDistance());
        routeDB.setPlanetDestination(route.getPlanetOrigin());
        routeDB.setPlanetDestination(route.getPlanetDestination());
        routeRepository.save(routeDB);
    }


    public void deleteRoute(int routeId) {
        log.info("#### start ShortestPathService :: deleteRoute #######");
        routeRepository.deleteById(routeId);
    }

}
