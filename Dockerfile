FROM openjdk:8
ADD target/homeloanapp1-0.0.1.jar homeloanapp1-0.0.1.jar
ENTRYPOINT ["java","-jar","homeloanapp1-0.0.1.jar"]
EXPOSE 8080