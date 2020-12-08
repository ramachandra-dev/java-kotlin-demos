<p align="center" width="100%">
    <img src="logo.png"/>
</p>
Welcome to the Plan Generator API.
# General

Plan Generator API provides the repayment schedule plan for a lifetime of a loan.

All the implementations are written natively in **Java**, **Spring Boot**, **Swagger2**,  ** and Docker**

Prerequisites for calculating a repayment plan specific input parameters are necessary:

- Duration (Number of installments in months)
- Nominal rate (Annual interest rate)
- Loan amount (Principal amount)
- Date of Disbursement/Payout ("startDate")

	The goal is to calculate a repayment plan for an annuity loan. Therefore the amount that the borrower has to pay back every month, 
	consisting of principal and interest repayments, does not change (the last installment might be an exception).
	The annuity amount has to be derived from three of the input parameters (duration, nominal interest rate, total loan amount).

**Reference**:  [Annuity Payment Formula](http://financeformulas.net/Annuity_Payment_Formula.html "Annuity Payment Formula")

** Note**: The nominal interest rate is an annual rate and must be converted to monthly before using in the annuity formula

# Requirements

* Minimum Java 11 or higher
* Maven 3.5.4 (or higher)

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
$ java -jar target/repayment-0.0.1-SNAPSHOT.jar
```

To exit the application, press **ctrl-c**.

### Docker

It is possible to run **Repayment* using Docker:

Build Docker image:

```bash
$ mvn clean package
$ docker build -t repayment:dev -f Dockerfile .
```


Sample Output for the Docker build image

```bash
Building the docker image with name repayment:dev...

Sending build context to Docker daemon  41.79MB
Step 1/6 : FROM openjdk:15-jdk-alpine
 ---> f02adfce91a2
Step 2/6 : EXPOSE 8080
 ---> Using cache
 ---> ecf8d04c57bc
Step 3/6 : ENV APP_HOME /usr/src/app
 ---> Using cache
 ---> 0cb16e79603d
Step 4/6 : COPY target/repayment-0.0.1-SNAPSHOT.jar $APP_HOME/app.jar
 ---> 5f7ff9e97a1c
Step 5/6 : WORKDIR $APP_HOME
 ---> Running in 889dcb3c769f
Removing intermediate container 889dcb3c769f
 ---> 22690159fffc
Step 6/6 : ENTRYPOINT exec java -jar app.jar
 ---> Running in e0408d12b814
Removing intermediate container e0408d12b814
 ---> 0bb2c8d0e8ea
Successfully built 0bb2c8d0e8ea
Successfully tagged repayment:dev

```

Run Docker container:

```bash
$ docker run --rm -i -p 8080:8080 --name repayment-c repayment:dev
```

##### Script

To build the project, build the docker images and run the docker image 

```bash
$ chmod +x script.sh
$ script.sh
```

Sample Script Response:

```bash

$ ./script.sh

Stop running Docker containers with image tag
a36649bf0af9

 Removing Docker containers with image tag
"docker rm" requires at least 1 argument.
See 'docker rm --help'.

Usage:  docker rm [OPTIONS] CONTAINER [CONTAINER...]

Remove one or more containers

Set Setting images name as repayment


Set Docker Image name as repayment:dev

Setting the port as 8080

Packaging the application...
[INFO] Scanning for projects...
[INFO]
[INFO] -------------------------< com.loan:repayment >-------------------------
[INFO] Building Plan Generator 0.0.1-SNAPSHOT
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  22.033 s
[INFO] Finished at: 2020-11-22T12:21:47+01:00
[INFO] ------------------------------------------------------------------------

Building the docker image with name repayment:dev...

Sending build context to Docker daemon  41.79MB
Step 1/6 : FROM openjdk:15-jdk-alpine
 ---> f02adfce91a2
Step 2/6 : EXPOSE 8080
 ---> Using cache
 ---> ecf8d04c57bc
Step 3/6 : ENV APP_HOME /usr/src/app
 ---> Using cache
 ---> 0cb16e79603d
Step 4/6 : COPY target/repayment-0.0.1-SNAPSHOT.jar $APP_HOME/app.jar
 ---> 5f7ff9e97a1c
Step 5/6 : WORKDIR $APP_HOME
 ---> Running in 889dcb3c769f
Removing intermediate container 889dcb3c769f
 ---> 22690159fffc
Step 6/6 : ENTRYPOINT exec java -jar app.jar
 ---> Running in e0408d12b814
Removing intermediate container e0408d12b814
 ---> 0bb2c8d0e8ea
Successfully built 0bb2c8d0e8ea
Successfully tagged repayment:dev
SECURITY WARNING: You are building a Docker image from Windows against a non-Windows Docker host. All files and directories added to build context will have '-rwxr-xr-x' permissions. It is recommended to double check and reset permissions for sensitive files and directories.

Starting the docker image repayment:dev with name repayment..

```

## Tests

Tests can be run by executing following command from the root of the project:

```bash
$ mvn clean install -DskipTests=false
```
*Skipping tests*

```bash
$ mvn clean install -DskipTests
```
*Executing a particular test cases*
To execute a particular tests, run:

```bash
mvn clean install -Dtest=RepaymentPlanControllerIT
```
To execute a test method of a test, run:

```bash
mvn clean install -Dtest=RepaymentPlanControllerIT#testRepaymentPlan
```

### To test an api using curl

1. Get an oauth2 Access Token for Qualified user.

```bash
curl 2e2d8b3b-bf29-42a4-8194-d768a41453dd:d246d4a7-2d68-46e5-b053-1c937eeba9b7@localhost:8080/oauth/token -dgrant_type=client_credentials -dscope=any

Response:

{
	"access_token":"53713e93-1914-46f9-9f23-8162a0bf21b2",
	"token_type":"bearer",
	"expires_in":43199,
	"scope":"any"
}
```
2. Use access token as bearer to generate the loan payment schedule plan

```bash
	curl --location --request POST 'http://localhost:8080/generate-plan' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer 53713e93-1914-46f9-9f23-8162a0bf21b2' \
--data-raw '{
    "loanAmount": "5000",
    "nominalRate": "5",
    "duration": 12,
    "startDate": "2018-01-01T00:00:01Z"
}'
```
Response:

```json
{
    "borrowerPayments": [
        {
            "borrowerPaymentAmount": "428.04",
            "date": "2018-01-01T00:00:01Z",
            "initialOutstandingPrincipal": "5000.0",
            "interest": "20.83",
            "principal": "407.21",
            "remainingOutstandingPrincipal": "4592.79"
        },
        {
            "borrowerPaymentAmount": "428.04",
            "date": "2018-02-01T00:00:01Z",
            "initialOutstandingPrincipal": "4592.79",
            "interest": "19.14",
            "principal": "408.90",
            "remainingOutstandingPrincipal": "4183.89"
        },
        {
            "borrowerPaymentAmount": "428.01",
            "date": "2018-12-01T00:00:01Z",
            "initialOutstandingPrincipal": "426.23",
            "interest": "1.78",
            "principal": "426.23",
            "remainingOutstandingPrincipal": "0"
        }
    ]
}
```

### To test an api using Postman

1. Get Access Token

<p align="center">
    <img src="postman output 1.JPG" width="30%" length= "30%"/>
</p>

2. Access Token Generated

<p align="center">
    <img src="postman output 2.JPG" width="30%" length= "30%"/>
</p>

3. Use the Access Token as Bearer Authorization and repayment request payload for generate of plan

<p align="center">
    <img src="postman output 3.JPG" width="30%" length= "30%"/>
</p>

4. Response for Generate plan

<p align="center">
    <img src="postman output 4.JPG" width="30%" length= "30%"/>
</p>

### Swagger

1. Swagger Can be accessed using  http://localhost:8080/swagger-ui/

<p align="center">
    <img src="swagger api.JPG" width="30%" length= "30%"/>
</p>

2. Swagger API Docs can be access using http://localhost:8080/v3/api-docs

<p align="center">
    <img src="swagger api docs.JPG" width="30%" length= "30%"/>
</p>```
