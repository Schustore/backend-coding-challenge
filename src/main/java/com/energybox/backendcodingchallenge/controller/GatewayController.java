package com.energybox.backendcodingchallenge.controller;

import com.energybox.backendcodingchallenge.domain.Gateway;
import com.energybox.backendcodingchallenge.domain.GatewayQuery;
import com.energybox.backendcodingchallenge.service.GatewayService;
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
@RequestMapping( value = "/gateways" )
public class GatewayController {
    @Autowired
    private final GatewayService service;

    public GatewayController( GatewayService service ) {
        this.service = service;
    }

    public ResponseEntity<Object> responseObject(HttpStatus httpStatus, String message) {
        Map<String, Object> objectBody = new LinkedHashMap<>();
        objectBody.put("Current Timestamp", new Date());
        objectBody.put("Status", httpStatus.value());
        objectBody.put("Message", message);

        return new ResponseEntity<>(objectBody, httpStatus);
    }

    @ApiOperation( value = "create a gateway.", response = Object.class )
    @RequestMapping( value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Object> create(
            @Valid @RequestBody Gateway newGateway
    ) throws IOException, InterruptedException {
        this.service.createGateway(newGateway);

        return responseObject(HttpStatus.OK, String.format("gateway %s successfully created",newGateway.getName()));
    }

    @ApiOperation( value = "query for gateways.", response = Result.class )
    @RequestMapping( value = "/query", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Object> query(
            @Valid @RequestBody GatewayQuery gatewayQuery
    ) throws IOException, InterruptedException {
        Result response = this.service.query(gatewayQuery);

        return new ResponseEntity<>(response,  HttpStatus.OK );
    }
}
