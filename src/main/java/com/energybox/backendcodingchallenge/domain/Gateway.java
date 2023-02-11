package com.energybox.backendcodingchallenge.domain;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import javax.validation.constraints.NotBlank;

@NodeEntity
public class Gateway {

    @Id
    @NotBlank(message = "The name of the gateway is required!")
    private String name;

    public String getName (){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }


}
