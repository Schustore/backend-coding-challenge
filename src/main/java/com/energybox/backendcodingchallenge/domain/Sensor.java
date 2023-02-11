package com.energybox.backendcodingchallenge.domain;


import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@NodeEntity
public class Sensor {

    @Id
    @NotBlank(message = "The name of the sensor is required!")
    private String name;

    @NotNull(message = "sensor type is required.")
    private List<String> type;

    public Sensor(){
        this.type  = new ArrayList<>();
    }

    @Relationship(type = "CONNECTED_TO", direction = Relationship.OUTGOING)
    private Gateway gateway;
    public String getName(){
        return this.name;
    }

    public List<String> getType(){
        return this.type;
    }

    public Gateway getGateway(){return this.gateway; }

    public void setName(String name){
        this.name = name;
    }

    public void setType(List<String> type){
        this.type = type;
    }

    public void setGateway (Gateway gateway){ this.gateway = gateway; }

}
