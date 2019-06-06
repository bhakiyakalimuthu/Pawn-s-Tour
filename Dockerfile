
#Without Maven
#FROM openjdk:11
#ADD target/pawntour-boot.jar pawntour-boot.jar
#ENTRYPOINT ["java", "-jar", "/pawntour-boot.jar"]


FROM maven:3.6.1-jdk-11-slim as build
COPY . .
RUN mvn install

FROM openjdk:11
WORKDIR /app
COPY --from=build target/pawntour-boot.jar /app
ENTRYPOINT ["sh", "-c"]
CMD ["java -jar pawntour-boot.jar"]