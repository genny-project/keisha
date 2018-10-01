#!/bin/bash

./build.sh;docker rm -f cache-client ;docker run --name cache-client  --network genny-main_mainproxy  -v ${PWD}/target:/target  openjdk:latest java -jar /target/cache-server-0.0.1-SNAPSHOT-fat.jar
