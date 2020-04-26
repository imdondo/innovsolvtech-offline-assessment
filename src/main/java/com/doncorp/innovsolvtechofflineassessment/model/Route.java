package com.doncorp.innovsolvtechofflineassessment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int routeId;
    private String planetOrigin;
    private String planetDestination;
    private Double distance;
}
