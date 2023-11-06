FROM openjdk:8-jdk-alpine
EXPOSE 8089
ADD target/devops-integration.jar devops-integration.jar
ENTRYPOINT ["java","-jar","/devops-integration.jar"]
