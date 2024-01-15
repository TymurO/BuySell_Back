FROM openjdk:17-jdk-alpine
ADD ./target/buysell_back-0.0.1-SNAPSHOT.jar buysell-back.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/buysell-back.jar" ]