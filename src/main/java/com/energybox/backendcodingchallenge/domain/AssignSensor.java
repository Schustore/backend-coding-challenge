package com.energybox.backendcodingchallenge.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AssignSensor {

    @NotBlank(message = "sensor is required")
    private String sensor;

    @NotBlank(message = "gateway is required")
    private String gateway;

    @NotNull(message = "overwrite designation is required.")
    private Boolean overwrite;

    public Boolean getOverwrite(){
        return this.overwrite;
    }

    public String getSensor(){
        return this.sensor;
    }

    public String getGateway(){
        return this.gateway;
    }
    public void setOverwrite(Boolean overwrite) {
        this.overwrite = overwrite;
    }

    public void setSensor(String sensor){
        this.sensor = sensor;
    }

    public void setGateway(String gateway){
        this.gateway = gateway;
    }

}
