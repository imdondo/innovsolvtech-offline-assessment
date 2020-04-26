package com.doncorp.innovsolvtechofflineassessment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Traffic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int routeId;
    private String planetOrigin;
    private String planetDestination;
    private Double trafficDelay;
}
