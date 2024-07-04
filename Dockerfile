FROM openjdk:17-oracle
LABEL authors="hieut"
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
