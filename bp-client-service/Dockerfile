# Use maven to compile the java application.
FROM maven:3.9.5-eclipse-temurin-17-alpine AS build

# Set the working directory to /app
WORKDIR /workspace/app

# copy project content
COPY . ./

# Compile the application.
RUN mvn clean package -DskipTests -Dspring.profiles.active=docker -Pdocker

# Unpack the fatjar to create application layers
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

# Build runtime image.
FROM openjdk:17-jdk-alpine

# Adds an user and group and setting it as current user
RUN addgroup -S app && adduser -S app -G app
USER app

VOLUME /tmp

# Copy the application layers
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

# Starts java app from the entrypoint
ENTRYPOINT ["java", "-cp", "app:app/lib/*", "com.codingame.backendbp.bpclientservice.BpClientServiceApplication"]