Build Tool is Maven
Java 1.8 version is used (Please install java 1.8 version)

# Steps to run the Project:

Option i) Download whole project in an IDE and run main class as Spring Boot app (WalletApplication.java)
Option ii) Go to the directory customerWallet and run ---->  ./mvnw spring-boot:run
Option iii) or run ./mvnw clean package it will create an exceuatble jar file and run it using java -jar target/customerWallet-0.0.1-SNAPSHOT.jar

#NOTE

- server would start on port 8085. Please change server.port in application.properties, if you want to run it on a different port.
- Used inbuilt database H2 provided by SpringBoot and it would be available at runtime of the application. Dependency is added in Maven.


