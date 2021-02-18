<p align="center">
    <img src="logo.png"/>
</p>

Welcome to the Occupancy Manager Usage API.

# General

Occupancy Manager Usage API provides the usages of economy and premium rooms used and income earned.

All the implementations are written natively in **Java**, **Spring Boot**

Prerequisites for calculating occupancy usage requires few input parameters are necessary:

- Free Premium Rooms (Number of Premium rooms available )
- Free Economy Rooms (Number of Economy rooms available )
- Payments (Payments can be made by customers)
- Minimum Premium (Minimum Margin for premium upgrade)

# Requirements

- Minimum Java 11 or higher
- Maven 3.5.4 (or higher)

## How to run

### Maven

Open a terminal and run the following commands to ensure that Java and Maven installed:

```bash
$ java -version
java 15 2020-09-15
Java(TM) SE Runtime Environment (build 15+36-1562)
Java HotSpot(TM) 64-Bit Server VM (build 15+36-1562, mixed mode, sharing)
```

```bash
$ mvn -version
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: D:\apps\maven\bin\..
Java version: 15, vendor: Oracle Corporation, runtime: C:\Program Files\Java\jdk-15
Default locale: en_US, platform encoding: Cp1252
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
```

#### Using the Maven Plugin

The Spring Boot Maven plugin includes a run goal that can be used to quickly compile and run your application.

```bash
$ mvn spring-boot:run
```

#### Using Executable Jar

To create an executable jar run:

```bash
$ mvn clean package
```

To run that application, use the java -jar command, as follows:

```bash
$ java -jar target/manager-0.0.1-SNAPSHOT.jar
```

To exit the application, press **ctrl-c**.

## Tests

Tests can be run by executing following command from the root of the project:

```bash
$ mvn clean install -DskipTests=false
```

_Skipping tests_

```bash
$ mvn clean install -DskipTests
```

_Executing a particular test cases_
To execute a particular tests, run:

```bash
mvn clean install -Dtest=OccupancyManagerControllerIT
```

To execute a test method of a test, run:

```bash
mvn clean install -Dtest=OccupancyManagerControllerIT#testOccupanyUsage
```

### To test an api using curl

1. Use access token as bearer to generate the loan payment schedule plan

```bash
	curl --location --request 
	POST 'http://localhost:8080/occupancy-usage' 
	--header 'Content-Type: application/json' 
	--data-raw '{
    "Free Premium Rooms":7,
    "Free Economy Rooms":1,
    "Payments": [
        23,
        45,
        155,
        374,
        22,
        99,
        100,
        101,
        115,
        209
    ],
    "Minimum Premium": 100
  }'
```

Response:

```json
{
  "Usage Premium": "7 (EUR 1153)",
  "Usage Economy": "1 (EUR 45)"
}
```
