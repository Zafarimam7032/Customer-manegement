FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} Customer-Manegement-System.jar
ENTRYPOINT ["java","-jar","/Customer-Manegement-System.jar"]