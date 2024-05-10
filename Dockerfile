FROM openjdk:17-alpine
ARG JAR_FILE=target/coindesk-api-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} coindesk-api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/coindesk-api-0.0.1-SNAPSHOT.jar"]


#docker build -t coindesk-api .
#docker run -d -p 8080:8080 --name coindesk-api coindesk-api