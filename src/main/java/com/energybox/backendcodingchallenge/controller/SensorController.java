package com.energybox.backendcodingchallenge.controller;

import com.energybox.backendcodingchallenge.domain.Sensor;
import com.energybox.backendcodingchallenge.domain.SensorQuery;
import com.energybox.backendcodingchallenge.domain.AssignSensor;
import com.energybox.backendcodingchallenge.service.SensorService;
import io.swagger.annotations.ApiOperation;
import org.neo4j.ogm.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping( value = "/sensors" )
public class SensorController {

    @Autowired
    private final SensorService service;

    public SensorController ( SensorService service ) {
        this.service = service;
    }

    public ResponseEntity<Object> responseObject(HttpStatus httpStatus, String message) {
        Map<String, Object> objectBody = new LinkedHashMap<>();
        objectBody.put("Current Timestamp", new Date());
        objectBody.put("Status", httpStatus.value());
        objectBody.put("Message", message);

        return new ResponseEntity<>(objectBody, httpStatus);
    }

    @ApiOperation( value = "create a sensor.", response = Object.class )
    @RequestMapping( value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Object> create(
            @Valid @RequestBody Sensor sensor
    ) throws IOException, InterruptedException {
        this.service.createSensor(sensor);

        return responseObject(HttpStatus.OK, String.format("sensor %s successfully created.", sensor.getName()));
    }

    @ApiOperation( value = "query a sensor.", response = Result.class )
    @RequestMapping( value = "/query", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Object> query(
            @Valid @RequestBody SensorQuery sensorQuery
    ) throws IOException, InterruptedException {
        Result response = this.service.querySensors(sensorQuery);

        return new ResponseEntity<>(response,  HttpStatus.OK );
    }

    @ApiOperation( value = "assign a sensor to a gateway.", response = Object.class )
    @RequestMapping( value = "/assign", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Object> attach(
            @Valid @RequestBody AssignSensor assignSensor
    ) throws IOException, InterruptedException, IllegalAccessException {
        this.service.assignSensor(assignSensor);

        return responseObject(HttpStatus.OK, String.format("Sensor %s attached to gateway %s.", assignSensor.getSensor(), assignSensor.getGateway()));
    }


}
