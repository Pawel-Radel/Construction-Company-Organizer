FROM openjdk:15.0.2-jdk-buster
ADD target/construction-company-organizer-0.0.1-SNAPSHOT.jar .
EXPOSE 8000
CMD java -jar construction-company-organizer-0.0.1-SNAPSHOT.jar
