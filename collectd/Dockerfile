# Get the latest Ubuntu build from DockerHub
FROM ubuntu:latest

# Install Collectd
RUN apt-get update && apt-get install -y vim collectd

# Declare JMX Memory custom type for collectd
RUN echo "jmx_memory      value:GAUGE:0:U" >> /usr/share/collectd/types.db

# Copy the default collectd.conf file to the docker image
COPY	collectd.conf /etc/collectd/collectd.conf

# Copy the JMX metrics java plugin for collectd to the docker image
COPY jmx-metrics.conf /etc/collectd/collectd.conf.d

# Copy the curl_json java plugin for collectd to the docker image
COPY curl-json.conf /etc/collectd/collectd.conf.d

# Create directories to store external dependent libraries and keys
RUN mkdir	/clients
RUN mkdir	/keys

# Copy the dependent libraries and keys into docker image
COPY restConnector.jar /clients
COPY key.jks /keys

# Set the read/write/execute permissions for the dependent libraries and keys
RUN chmod 777 /clients/restConnector.jar
RUN chmod 777 /keys/key.jks

# Start the collectd process
CMD	["collectd", "-f"]
