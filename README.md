
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
    Details: 
    - Accepts one required parameter -- the name of the gateway you wish to create.
    - Creates a new gateway in neo4j database
   
- http://localhost:8082/api/v1/gateways/query

  Requestbody:
  ```
    {
     "allGateways": Boolean, //required
     "sensorType": String,   //optional
     "gateway": String       //optional
    }
  ```
     Details: 
    - Accepts 3 parameters. 1 is required, the other two are optional and narrow down the query.
    - Able to return all existing gateways in the database.  Setting "allGateways" to true will always return all existing gateways
    - Able to return all gateways with a specific sensor type assigned to it.
    - Able to return a specific gateway.
    - Able to return a specific gateway with specific sensors attached to it.
    
     
- http://localhost:8082/api/v1/sensors/create

  Requestbody:
  ```
    {
     "name": String,        //required
     "type": List<String> //required
    }
  ```
     Details: 
    - Accepts 2 parameters, both are required. This creates a new sensor in the neo4j database.
    - You can add as many types to the sensor as you wish upon creation.
    
- http://localhost:8082/api/v1/sensors/assign

  Requestbody:
  ```
    {
     "gateway": String,  //required
     "sensor": String,   //required
     "overwrite": Boolean  //required
    }
  ```
     Details: 
    - Accepts 3 parameters, all 3 are required. This assigns a specific sensor to a specific gateway.
    - The sensor and gateway need to exist in the database.
    - The overwrite option will decide to overwrite a relationship if one already exists on the sensor.
     
- http://localhost:8082/api/v1/sensors/query

  Requestbody:
    ```
     {
       "allSensors":Boolean,  //required
       "sensorType": String,  //optional
       "gateway": String     //optional
     }
    ```
     Details: 
    - Accepts 3 parameters. 1 is required and 2 are optional, they help narrow down the query.
    - Able to return all existing sensors in the database. Setting allSensors to true will always return all sensors.
    - Able to return all sensors with a specific type assigned to it.
    - Able to return all sensors that have been assigned to specific gateway.
    - Able to return all sensors that have been assigned to a specific gateway and have a specific type assigned to them.

