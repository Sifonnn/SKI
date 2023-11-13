FROM openjdk:11

WORKDIR /app/

RUN curl -X GET http://admin:amine12@172.20.10.7:8081/repository/maven-releases/tn/esprit/spring/gestion-station-ski/2.0/gestion-station-ski-2.0.jar --output app.jar

EXPOSE 8089

ENTRYPOINT ["java","-jar","app.jar"]
