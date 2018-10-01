FROM openjdk:8u151-jre-alpine3.7

COPY target/cache-server-0.0.1-SNAPSHOT-fat.jar /service.jar

ENTRYPOINT ["sh", "-c"]

CMD ["exec java -jar /service.jar"]
