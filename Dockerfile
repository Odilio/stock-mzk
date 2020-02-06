FROM maven:3-jdk-11-slim AS build

WORKDIR /build

COPY . .
RUN mvn package -X

FROM openjdk:11-alpine

ENV VERTICLE_FILE=mzk-1.0.0-SNAPSHOT-fat.jar \
    VERTICLE_HOME=/usr/verticles

EXPOSE 8080

# Copy your verticle to the container       (4)
COPY --from=build /build/target/$VERTICLE_FILE $VERTICLE_HOME/

# Launch the verticle                       (5)
WORKDIR $VERTICLE_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar $VERTICLE_FILE"]