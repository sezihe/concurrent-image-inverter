# Basic Image Inverter
### [Encentral Solutions](https://www.encentralsolutions.com/)

A Basic Image Inverter Application.

#### Inverting Rules:
```
Red -> Green

Green -> Blue

Blue -> Red
```

## How to Use
- Clone this repo to your local computer
- Open up terminal and run ``mvn clean install``
- ``cd`` into 'service-gateway' module and run ``mvn play2:run``
- Proceed to your browser to test the endpoints. Swagger URL ``http://localhost:9000/docs?url=http://localhost:9000/api-docs.json#/``



## Technologies Used
- Play Framework
- Akka Toolkit
- Maven (Build Tool)
