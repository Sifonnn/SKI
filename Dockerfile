FROM openjdk:8-jdk-alpine
EXPOSE 8089
ADD target/gestion-station-ski.jar gestion-station-ski.jar
ENTRYPOINT ["java","-jar","/gestion-station-ski.jar"]
