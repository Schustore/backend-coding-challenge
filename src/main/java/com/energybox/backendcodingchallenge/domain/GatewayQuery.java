package com.energybox.backendcodingchallenge.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class GatewayQuery {

    @NotNull(message = "You need to specify if you want all gateways or not.")
    private Boolean allGateways;

    @Pattern(regexp = "electricity|humidity|temperature", message = "sensor type must be: electricity, humidity, or temperature")
    private String sensorType;

    private String gateway;

    public String getGateway(){
        return this.gateway;
    }

    public Boolean getAllGateways(){
        return this.allGateways;
    }

    public String getSensorType(){
        return this.sensorType;
    }

    public void setGateway(String gateway){
        this.gateway = gateway;
    }
    public void setAllGateways(Boolean allGateways){
        this.allGateways = allGateways;
    }

    public void setSensorType(String sensorType){
        this.sensorType = sensorType;
    }

}
