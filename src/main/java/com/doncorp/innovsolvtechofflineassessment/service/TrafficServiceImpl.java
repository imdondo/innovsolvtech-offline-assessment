package com.doncorp.innovsolvtechofflineassessment.service;

import com.doncorp.innovsolvtechofflineassessment.model.Traffic;
import com.doncorp.innovsolvtechofflineassessment.repository.TrafficRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrafficServiceImpl implements TrafficService {
    @Autowired
    private TrafficRepository repository;

    public TrafficServiceImpl(TrafficRepository repository) {
    }

    public List<Traffic> findAll() {
        List<Traffic> list = new ArrayList<>();
        repository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Traffic findOne(String name) {
        return null;
    }


    @Override
    public Traffic findById(int id) {
        Optional<Traffic> optionalTraffic = repository.findById(id);
        return optionalTraffic.orElse(null);
    }


    @Override
    public Traffic update(Traffic traffic) {
        if (traffic != null) {
            BeanUtils.copyProperties(traffic, traffic);
            repository.save(traffic);
        }
        return traffic;
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }


    @Override
    public Traffic save(Traffic traffic) {
        Traffic newTraffic = new Traffic();
        newTraffic.setPlanetOrigin(traffic.getPlanetOrigin());
        newTraffic.setPlanetDestination(traffic.getPlanetDestination());
        newTraffic.setTrafficDelay(traffic.getTrafficDelay());
        return repository.save(newTraffic);
    }
}
