FROM openjdk:8u151-jre-alpine3.7

COPY target/keisha-fat.jar /service.jar

ENTRYPOINT ["sh", "-c"]

CMD ["exec java -jar /service.jar"]
