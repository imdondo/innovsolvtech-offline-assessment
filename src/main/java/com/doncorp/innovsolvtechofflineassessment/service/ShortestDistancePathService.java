package com.doncorp.innovsolvtechofflineassessment.service;

import com.doncorp.innovsolvtechofflineassessment.algorithm.Dijkstra;
import com.doncorp.innovsolvtechofflineassessment.model.*;
import com.doncorp.innovsolvtechofflineassessment.repository.PlanetRepository;
import com.doncorp.innovsolvtechofflineassessment.repository.RouteRepository;
import com.doncorp.innovsolvtechofflineassessment.repository.ShortestDistancePathRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ShortestDistancePathService {

    @Autowired
    PlanetRepository planetRepository;

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    ShortestDistancePathRepository shortestDistancePathRepository;

    public String shortestPath(String sourceNode, String destinationNode) {
        log.info("###### start :: shortestPath ########");
        List<Planet> planetNames = (List<Planet>)planetRepository.findAll();
        List<Node> listNode = new ArrayList<>();
        planetNames.forEach(s -> {
            Node node = new Node(s.getPlanetNode());
            listNode.add(node);
        });

        List<Route> routes = (List<Route>)routeRepository.findAll();
        listNode.forEach(n -> {
            addDestination(n, listNode, routes);
        });

        Graph graph1 = new Graph();
        for (Node node : listNode) {
            graph1.addNode(node);
        }
        graph1 = Dijkstra.calculateShortestPathFromSource(graph1, listNode.get(0));

       log.info("####### after graph1 #########");
        StringBuffer sb = new StringBuffer();
        for( Node node:graph1.getNodes()) {

            if(node.getName().equalsIgnoreCase(destinationNode)) {
                for(Node n: node.getShortestPath()) {
                    log.info(" getShortestPath ##### >>>>>>>>>>> "+n.getName());
                    sb.append(n.getName()).append("->");
                }
            }

        }


        for (Planet planetName : planetNames) {
            ShortestPath shortestPath = new ShortestPath();
            for (Node node : graph1.getNodes()) {
                if (node.getName().equalsIgnoreCase(planetName.getPlanetNode())) {
                    shortestPath.setId((long) planetName.getId());
                    shortestPath.setPlanetNode(node.getName());
                    shortestPath.setPlanetName(planetName.getPlanetName());
                    shortestPath.setPath(node.getPath());
                }

            }
            shortestDistancePathRepository.save(shortestPath);
        }
        log.info("###### end :: shortestPath ########");
        return sb.append(destinationNode).toString();
    }

    private void addDestination(Node n, List<Node> listNode, List<Route> routes) {
        log.info("###### start :: addDestination ########");
        routes.forEach(r -> {
            if (r.getPlanetOrigin().equalsIgnoreCase(n.getName())) {
                listNode.forEach(l -> {
                    if (l.getName().equalsIgnoreCase(r.getPlanetDestination())) {
                        n.addDestination(l, r.getDistance().floatValue());
                    }
                });
            }
        });
        log.info("###### end :: addDestination ########");
    }

}
