FROM openjdk:11
ADD target/customer-manegement-system.jar  customer-manegement-system.jar
EXPOSE 9090
ENTRYPOINT ["java" , "-jar", "customer-manegement-system.jar"]