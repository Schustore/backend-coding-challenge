package com.energybox.backendcodingchallenge.service;

import com.energybox.backendcodingchallenge.domain.AssignSensor;
import com.energybox.backendcodingchallenge.domain.Gateway;
import com.energybox.backendcodingchallenge.domain.Sensor;
import com.energybox.backendcodingchallenge.domain.SensorQuery;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class SensorService {

    private SessionFactory sessionFactory;
    public SensorService() {
        Configuration config = new Configuration.Builder()
                .uri("neo4j://localhost:7687")
                .credentials("neo4j", "energyBox")
                .build();

        this.sessionFactory = new SessionFactory(config, "com.energybox.backendcodingchallenge.domain");
    }

    public void createSensor(Sensor sensor){
        Session session = sessionFactory.openSession();
        session.save(sensor);
    }

    public Result querySensors (SensorQuery sensorQuery){
        Session session = sessionFactory.openSession();
        String relationship =  "";
        String typeOfSensor = "";

        if (sensorQuery.getAllSensors()!= true){

            if(sensorQuery.getGateway() != null){
                relationship = String.format("-[r:CONNECTED_TO]->(c:Gateway{name:\"%s\"})", sensorQuery.getGateway());
            }

            if(sensorQuery.getSensorType()!= null){
                typeOfSensor = String.format("WHERE (\"%s\" in n.type)",sensorQuery.getSensorType());
            }

        }

        String finalQuery = String.format("MATCH (n:Sensor)%s %s return n.name as name, n.type as type",relationship,typeOfSensor);
        Result result = session.query(finalQuery, Collections.emptyMap());

        return result;
    }

    public void assignSensor(AssignSensor assignSensor) throws IllegalAccessException {
        Session session = sessionFactory.openSession();
        String sensorQuery = "MATCH (n:Sensor{name:$name}) return n";
        String gateWayQuery = "MATCH (n:Gateway{name:$name}) return n";

        Sensor sensor = session.queryForObject(Sensor.class, sensorQuery, Map.of("name", assignSensor.getSensor()));
        Gateway gateway = session.queryForObject(Gateway.class, gateWayQuery, Map.of("name", assignSensor.getGateway()));

        if(sensor == null && gateway == null){
            throw new IllegalAccessException("sensor and gateway do not exist");
        } else if(sensor == null){
            throw new IllegalAccessException("sensor does not exist.");
        } else if(gateway == null){
            throw new IllegalAccessException("gateway does not exist.");
        }

        String checkRelationshipQuery =  "MATCH (n:Sensor {name:$name})-[r:CONNECTED_TO]->(c) RETURN n";
        Sensor isRelationship = session.queryForObject(Sensor.class, checkRelationshipQuery, Map.of("name",assignSensor.getSensor()));

        if(isRelationship != null){
            if(assignSensor.getOverwrite()){
                String removeRelationship = String.format("MATCH (n:Sensor {name:\"%s\"})-[r:CONNECTED_TO]->(c) DELETE r",assignSensor.getSensor());
                session.query(removeRelationship, Collections.emptyMap());
            }else{
                throw new IllegalAccessException("Sensor is already assigned to another gateway. Change the overwrite flag to overwrite the relationship.");
            }
        }

        sensor.setGateway(gateway);
        session.save(sensor);

    }
}
