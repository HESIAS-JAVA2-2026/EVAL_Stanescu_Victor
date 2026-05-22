FROM maven:3.9-eclipse-temurin-25 as MAVEN_TOOL_CHAIN_CACHE
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY ./pom.xml /tmp/
COPY ./src /tmp/src/
WORKDIR /tmp/
RUN mvn clean package -DskipTests

FROM eclipse-temurin:25-jre-noble
COPY --from=MAVEN_TOOL_CHAIN_CACHE /tmp/target/demo-1.0-SNAPSHOT.jar /demo.jar
EXPOSE 8081
ENV _JAVA_OPTIONS "-Dspring.profiles.active=cloud"
ENTRYPOINT ["java", "-jar", "/demo.jar"]