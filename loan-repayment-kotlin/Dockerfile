FROM openjdk:15-jdk-alpine

EXPOSE 8080

ENV APP_HOME /usr/src/app

COPY target/repayment-0.0.1-SNAPSHOT.jar $APP_HOME/app.jar

WORKDIR $APP_HOME

ENTRYPOINT exec java -jar app.jar

