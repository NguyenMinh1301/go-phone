# ===== STAGE 1: Build with Maven =====
FROM maven:3.9-amazoncorretto-21 AS build
WORKDIR /workspace
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -DskipTests -Dspotless.check.skip=true clean package

RUN find target -maxdepth 1 -type f -name "*.jar" ! -name "*original*" -exec cp {} /workspace/app.jar \;

# ===== STAGE 2: Runtime =====
FROM amazoncorretto:21-alpine
RUN addgroup -S app && adduser -S app -G app
USER app
WORKDIR /app
COPY --from=build /workspace/app.jar /app/app.jar
EXPOSE 8080
ENV SPRING_PROFILE=dev
ENV JAVA_OPTS=""
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar --spring.profiles.active=${SPRING_PROFILE}"]
