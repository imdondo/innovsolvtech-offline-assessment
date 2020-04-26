package com.doncorp.innovsolvtechofflineassessment.controller;

import com.doncorp.innovsolvtechofflineassessment.model.Planet;
import com.doncorp.innovsolvtechofflineassessment.model.Route;
import com.doncorp.innovsolvtechofflineassessment.model.ShortestPath;
import com.doncorp.innovsolvtechofflineassessment.repository.ShortestDistancePathRepository;
import com.doncorp.innovsolvtechofflineassessment.service.ShortestDistancePathService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/api/shortestpath")
public class ShortestPathController {

    @Autowired
    private ShortestDistancePathRepository shortestDistanceRepository;

    @Autowired
    private ShortestDistancePathService shortestPathService;


    @GetMapping("/")
    public String loadUIPage(Model model) {
        model.addAttribute("planetNames", new Route());
        return "displayUI";
    }



    @PostMapping("/findshortestpath")
    public String findshortestpath(Model model, @ModelAttribute Route planetNames) {
        log.info("###### start :: findshortestpath ######## with sourceNode "+planetNames.getPlanetOrigin()+ "and distination Name "+planetNames.getPlanetDestination());

        ShortestPath shortestDistance = new ShortestPath();
        List<ShortestPath> listShortestDistance = (List<ShortestPath>)shortestDistanceRepository.findAll();
        listShortestDistance.forEach(l -> {
            if (l.getPlanetNode().equalsIgnoreCase(planetNames.getPlanetOrigin())) {
                shortestDistance.setPath(l.getPath());

            }
        });

        String shortestPath = shortestPathService.shortestPath(planetNames.getPlanetOrigin(), planetNames.getPlanetDestination());
        log.info("###### end :: findshortestpath ######## ");
        model.addAttribute("shortestPath", shortestPath);
        return "success";
    }



}
