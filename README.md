
### Tech to use:

- Java
- Spring Boot
- Spring Data
- Maven
- Neo4J (Use of an object graph mapper is completely fine and encouraged)

### Getting Started

You'll need to set up a local Neo4J instance. A docker-compose file is included in the sample project for convenience.

Don't feel obligated to spend any more than 2-5 hours on this. You're welcome to spend more time if you're having fun, but you won't be judged on how much you complete.

Project POM file is purposefully bare. Feel free to add dependencies of your choosing. 

---

Our company is rolling out a new backend service that will allow developers to create Sensors and Gateways in our system. 
A sensor can only be connected to one Gateway at a time, while a Gateway can have N sensors connected to it.

A relationship of type CONNECTED_TO should be created between sensor and gateway when a sensor is assigned to a given gateway.

Sensors should have a type attribute. And a sensor can have multiple types assigned to it. 

Some example of sensor types could be temperature, humidity, electricity.

Try to complete the user stories below as best you can, and feel free to add in any features you'd like to see that may not be detailed here. The stories are purposefully vague and open to interpretation.

### User Stories:

- As a user I'd like to query for all the existing sensors.

- As a user I'd like to query for all gateways

- As a user I'd like to query all sensors assigned to an existing gateway

- As a user I'd like to create a new sensor

- As a user I'd like to create a new gateway

- As a user I'd like to assign a sensor to a given gateway.

- As a user I'd like to query for sensors of a certain type.

- As a user I'd like to query for a gateway that has electrical sensors assigned to it.

Bonus:

- Sensors can have a last_reading for each sensor type. The last_reading consists of a timestamp and a value.

- As a user I'd like to query all of the existing last_readings of a given sensor.




# API Information

There are 5 different endpoints produced from this springboot application. These endpoints, and request bodies, are listed below:

- http://localhost:8082/api/v1/gateways/create

  Requestbody:
  ```
    {
       "name": String  //required
    }
  ```
    Description: This endpoint only has one parameter, the name of the gateway you wish to create. It will create a new gateway in the neo4j database.
   
- http://localhost:8082/api/v1/gateways/query

  Requestbody:
  ```
    {
     "allGateways": Boolean, //required
     "sensorType": String,   //optional
     "gateway": String       //optional
    }
  ```
     Description: This endpoint accepts up to 3 parameters. It handles all querying for gateways and can return all gateways, all gateways with specific sensor types assigned to them, a specific gateway, and a specific gateway that also has specific sensor type assigned to it.
     
- http://localhost:8082/api/v1/sensors/create

  Requestbody:
  ```
    {
     "name": String,        //required
     "type": List<String> //required
    }
  ```
     Description: This endpoint accepts 2 parameters. It creates a new sensor and adds it to the neo4j database. You can add as many sensor types to it as you'd like.
     
- http://localhost:8082/api/v1/sensors/assign

  Requestbody:
  ```
    {
     "gateway": String,  //required
     "sensor": String,   //required
     "overwrite": Boolean  //required
    }
  ```
     Description: This endpoint accepts 3 parameters. It assigns a specified sensor to a specified gateway. The gateways need to exist in the neo4j database. There is an option to overwrite an existing relationship.
     
- http://localhost:8082/api/v1/sensors/query

  Requestbody:
    ```
     {
       "allSensors":Boolean,  //required
       "sensorType": String,  //optional
       "gateway": String     //optional
     }
    ```
     Description: This endpoint accepts up to 3 parameters. It is responsible for all querying surrounding sensors. It can return all of the existing sensors, all sensors of a specific type, all sensors that have been assigned to a gateway (can narrow down by type too), and all sensors that have been assigned to a specific gateway (can narrow down by type too).

