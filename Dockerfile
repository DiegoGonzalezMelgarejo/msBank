FROM openjdk:11.0.16-jdk

WORKDIR /app

COPY ./target/diego-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

ENTRYPOINT ["java","-jar", "diego-0.0.1-SNAPSHOT.jar"]