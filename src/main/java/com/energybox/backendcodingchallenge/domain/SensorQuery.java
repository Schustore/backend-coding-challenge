package com.energybox.backendcodingchallenge.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class SensorQuery {

    @NotNull(message = "You need to specify if you want all sensors or not.")
    private Boolean allSensors;
    private String gateway;
    @Pattern(regexp = "electricity|humidity|temperature", message = "sensor type must be: electricity, humidity, or temperature")
    private String sensorType;

    public Boolean getAllSensors(){
        return this.allSensors;
    }
    public String getGateway(){
        return this.gateway;
    }

    public String getSensorType(){
        return this.sensorType;
    }

    public void setAllSensors(Boolean allSensors){
        this.allSensors = allSensors;
    }
    public void setGateway(String gateway){
        this.gateway = gateway;
    }

    public void setSensorType(String sensorType){
        this.sensorType = sensorType;
    }

}
