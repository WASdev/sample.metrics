#!/bin/bash
# A simple shell script to build, configure, and run docker containers for collectd and graphite

echo "Copying the restConnector.jar library from the Liberty Docker Container to the host..."
docker cp liberty:/opt/ibm/wlp/clients/restConnector.jar .

echo "Copying the Liberty keystore keys.jks from the Liberty Docker Container to the host..."
docker cp liberty:/opt/ibm/wlp/output/server1/resources/security/key.jks .

echo "Building the collectd Docker image..."
docker build -t example:collectd .

echo "Running the Graphite container from DockerHub..."
docker run --name graphite -d -p 80:80 -p 2003:2003 hopsoft/graphite-statsd

echo "Running the collectd container..."
docker run --name collectd -d --link liberty --link graphite example:collectd
