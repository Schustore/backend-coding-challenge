package com.energybox.backendcodingchallenge.service;

import com.energybox.backendcodingchallenge.domain.Gateway;
import com.energybox.backendcodingchallenge.domain.GatewayQuery;
import org.neo4j.ogm.session.SessionFactory;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.model.Result;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GatewayService {

    private SessionFactory sessionFactory;
    public GatewayService() {
        Configuration config = new Configuration.Builder()
                .uri("neo4j://localhost:7687")
                .credentials("neo4j", "energyBox")
                .build();

         this.sessionFactory = new SessionFactory(config, "com.energybox.backendcodingchallenge.domain");
    }
    public void createGateway(Gateway gateway){
        Session session = sessionFactory.openSession();
        session.save(gateway);

    }

    public Result query(GatewayQuery gatewayQuery) {
        Session session = sessionFactory.openSession();
        String relationship = "";
        String typeOfSensor = "";

        if (gatewayQuery.getAllGateways() != true) {

            if (gatewayQuery.getSensorType() != null) {
                relationship = "(m:Sensor)-->";
                typeOfSensor = String.format("WHERE (\"%s\" in m.type)", gatewayQuery.getSensorType());
            }

        }

        String finalQuery = String.format("MATCH %s(n:Gateway) %s return n.name as name", relationship, typeOfSensor);
        Result result = session.query(finalQuery, Collections.emptyMap());

        return result;
    }
}
