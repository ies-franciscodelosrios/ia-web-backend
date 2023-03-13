FROM openjdk:8-alpine
COPY "./target/ia-web-backend-0.0.1-SNAPSHOT.jar" "app.jar"
ENTRYPOINT ["java","-jar","app.jar"]