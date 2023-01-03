FROM openjdk:17-alpine3.14
COPY target/*.jar providerapi.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","providerapi.jar"]