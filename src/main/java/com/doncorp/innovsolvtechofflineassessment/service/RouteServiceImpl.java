package com.doncorp.innovsolvtechofflineassessment.service;

import com.doncorp.innovsolvtechofflineassessment.model.Route;
import com.doncorp.innovsolvtechofflineassessment.repository.RouteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {
    @Autowired
    private RouteRepository repository;

    public RouteServiceImpl(RouteRepository repository) {
    }

    public List<Route> findAll() {
        List<Route> list = new ArrayList<>();
        repository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Route findOne(String name) {
        return null;
    }


    @Override
    public Route findById(int id) {
        Optional<Route> optionalRoute = repository.findById(id);
        return optionalRoute.orElse(null);
    }


    @Override
    public Route update(Route route) {
        if (route != null) {
            BeanUtils.copyProperties(route, route);
            repository.save(route);
        }
        return route;
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }


    @Override
    public Route save(Route route) {
        Route newRoute = new Route();
        newRoute.setPlanetOrigin(route.getPlanetOrigin());
        newRoute.setPlanetDestination(route.getPlanetDestination());
        newRoute.setDistance(route.getDistance());
        return repository.save(newRoute);
    }
}
